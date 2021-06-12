package com.graphql.deliveryShare2.sample.AboutChat;
import com.graphql.deliveryShare2.sample.AboutUser.UserRepository;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.graphql.deliveryShare2.sample.AboutChat.ChatRepository;
import com.graphql.deliveryShare2.sample.AboutChat.ChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.transaction.Transactional;

import graphql.schema.DataFetcher;

@Component
public class MessageDataFetcher {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;
 

    @Autowired
    public MessageDataFetcher(MessageRepository messageRepository, UserRepository userRepository, ChatRepository chatRepository){
      this.messageRepository=messageRepository;
      this.userRepository=userRepository;
      this.chatRepository = chatRepository;
    
    }
  
    public DataFetcher<?> allMessages () {
      return environment -> {
        return messageRepository.findAll();
      };
    }
  
    public DataFetcher<?> Message () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return messageRepository.findBySeq(seq);
      };
    }

    public UserEntity getFromUser(MessageEntity messageEntity) {
      return userRepository.findBySeq(messageEntity.getFromUser().getSeq());
    }

    public UserEntity getToUser(MessageEntity messageEntity) {
      return userRepository.findBySeq(messageEntity.getToUser().getSeq());
    }

    public DataFetcher<?> sendMessage(){
      return environment -> {
        MessageEntity messageEntity = new MessageEntity();
        ChatEntity chatEntity = new ChatEntity();
        String text=environment.getArgument("text");
        int toseq = environment.getArgument("toseq");
       
        int chat_seq = environment.getArgument("chat_seq");

        ChatEntity chat = chatRepository.findBySeq(chat_seq);
        

        if(chat.getIsActive() == false){
          return null;
        }

        else{
          messageEntity.setText(text);
          messageEntity.setToseq(toseq);
          messageEntity.setFromseq(10);
          messageEntity.setChatSeq(chat_seq);
          messageEntity.setCreatedAt();
          messageEntity.setIsRead0();
        
   
  
          messageRepository.save(messageEntity);

          int seq = messageEntity.getSeq();
          
          chat.setLastMessage(seq);

          messageRepository.save(messageEntity);
          try {
            URL url = new URL ("https://exp.host/--/api/v2/push/send");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("host", "exp.host");
            con.setRequestProperty("content-Type", "application/json");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("accept-encoding", "gzip, deflate");
            con.setDoOutput(true);
            String jsonInputString = "{\"to\": \"ExponentPushToken[usertoken]\",\"title\": \"새로운 메세지\", \"body\": "+text+"}";
            try(OutputStream os = con.getOutputStream()) {
              byte[] input = jsonInputString.getBytes("utf-8");
              os.write(input, 0, input.length);			
          }
          try(BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
              response.append(responseLine.trim());
            }
            System.out.println(response.toString());
          }
  
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        
        
          return messageEntity;

        }

        
      };
    }

    public DataFetcher<?> readMessage () {
      return environment -> {
        int seq = environment.getArgument("seq");
        MessageEntity message = messageRepository.findBySeq(seq);
        message.setIsRead();
        messageRepository.save(message);
        return true;

      };
    }
  

    

  
}
