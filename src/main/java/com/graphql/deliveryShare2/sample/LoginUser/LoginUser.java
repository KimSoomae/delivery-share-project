package com.graphql.deliveryShare2.sample.LoginUser;

import com.graphql.deliveryShare2.sample.AboutUser.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Getter
@Builder
@Table(name = "login_user")
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LoginUser {
    @Id
    @Column(name = "user_seq", insertable = false, updatable = false)
    @EqualsAndHashCode.Include
    private Long userSeq;
    @EqualsAndHashCode.Include
    private String email;
    @Setter
    @EqualsAndHashCode.Include
    private String password;
    @ElementCollection
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_seq"))
    @Column(name = "name", insertable = true)
    private Set<String> roles;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_seq")
    private UserEntity user;
    private final String token;

    public LoginUser(String token, UserEntity user){
        this.token=token;
        this.user=user;
    }



}