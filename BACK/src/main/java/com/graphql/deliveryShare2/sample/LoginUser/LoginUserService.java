package com.graphql.deliveryShare2.sample.LoginUser;

import com.graphql.deliveryShare2.config.security.*;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static com.graphql.deliveryShare2.StreamUtils.collectionStream;
import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class LoginUserService implements UserDetailsService {
    private static final String ADMIN_AUTHORITY = "ADMIN";
    private static final String USER_AUTHORITY = "USER";
    private final LoginUserRepository repository;
    //private final PersonService personService;
    private final SecurityProperties properties;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public JWTUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository
            .findByEmail(email)
            .map(user -> getUserDetails(user, getToken(user)))
            .orElseThrow(() -> new UsernameNotFoundException("Username or password didn''t match"));
    }

    @Transactional
    public JWTUserDetails loadUserByToken(String token) {
        return getDecodedToken(token)
            .map(DecodedJWT::getSubject)
            .flatMap(repository::findByEmail)
            .map(user -> getUserDetails(user, token))
            .orElseThrow(BadTokenException::new);
    }

    @Transactional
    public LoginUser getCurrentUser() {
        System.out.println("여기까지 왔따");
        return Optional
            .ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getName)
            .flatMap(repository::findByEmail)
            .orElse(null);
    }

    @Transactional
    public LoginUser createLoginUser(UserEntity user, CreateLoginUserInput input) {
        if (!exists(input)) {
            return repository.saveAndFlush(LoginUser
                .builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .roles(Set.of(USER_AUTHORITY))
                .userSeq(user.getSeq())
                .user(user)
                .build());
        } else {
            throw new LoginUserAlreadyExistsException(input.getEmail());
        }
    }

    //@Transactional
    //public LoginUser updatePassword(Long userSeq, UpdatePasswordInput input) {
    //    LoginUser loginUser = repository.findBySeq(userSeq).orElseThrow(() -> new LoginUserNotFoundException(userSeq));
    //    if (passwordEncoder.matches(input.getOriginalPassword(), loginUser.getPassword())) {
    //        loginUser.setPassword(passwordEncoder.encode(input.getNewPassword()));
    //    } else {
    //        throw new BadCredentialsException(loginUser.getEmail());
    //    }
    //    return loginUser;
   // }

    @Transactional
    public String getToken(LoginUser loginUser) {
        Instant now = Instant.now();
        Instant expiry = Instant.now().plus(properties.getTokenExpiration());
        return JWT
            .create()
            .withIssuer(properties.getTokenIssuer())
            .withIssuedAt(Date.from(now))
            .withExpiresAt(Date.from(expiry))
            .withSubject(loginUser.getEmail())
            .sign(algorithm);
    }

    public boolean isAdmin() {
        return Optional
            .ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getAuthorities)
            .stream()
            .flatMap(Collection::stream)
            .map(GrantedAuthority::getAuthority)
            .anyMatch(ADMIN_AUTHORITY::equals);
    }

    public boolean isAuthenticated() {
        return Optional
            .ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .filter(not(this::isAnonymous))
            .isPresent();
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken;
    }

    @Transactional
    public boolean deleteUser(Long userSeq) {
        if (repository.existsById(userSeq)) {
            repository.deleteById(userSeq);
            return true;
        } else {
            return false;
        }
    }


    private boolean exists(CreateLoginUserInput input) {
        return repository.existsByEmail(input.getEmail());
    }

    private JWTUserDetails getUserDetails(LoginUser loginUser, String token) {
        return JWTUserDetails
            .builder()
            .username(loginUser.getEmail())
            .password(loginUser.getPassword())
            .authorities(collectionStream(loginUser.getRoles())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()))
            .token(token)
            .build();
    }

    private Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch(JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}