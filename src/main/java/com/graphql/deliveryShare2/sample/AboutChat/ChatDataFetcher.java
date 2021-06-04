package com.graphql.deliveryShare2.sample.AboutChat;

import com.graphql.deliveryShare2.sample.AboutUser.UserRepository;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

import java.util.List;

@Component
public class ChatDataFetcher {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ChatDataFetcher(ChatRepository chatRepository, MessageRepository messageRepository, UserRepository userRepository) {
      this.chatRepository = chatRepository;
      this.messageRepository = messageRepository;
      this.userRepository = userRepository;
     }
  
    public DataFetcher<?> allChats () {
      return environment -> {
        return chatRepository.findAll();
      };
    }
  
    public DataFetcher<?> Chat () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return chatRepository.findBySeq(seq);
      };
    }

    public List<MessageEntity> getMessage(ChatEntity chatEntity) {
      return messageRepository.findAll();
      

    }

    public List<UserEntity> getUser(ChatEntity chatEntity) {
      return userRepository.findAll();
      

    }

    public MessageEntity getLastMessage(ChatEntity chatEntity) {
      return messageRepository.findBySeq(chatEntity.getLastMessage().getSeq());
    }

    public DataFetcher<?> quitChat(){
      return environment -> {
        int seq=environment.getArgument("seq");
        chatRepository.deleteBySeq(seq);
        return true;
      };
    }
  
}