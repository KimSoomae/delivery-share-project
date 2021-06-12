package com.graphql.deliveryShare2.sample.AboutUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

import graphql.schema.DataFetcher;
import com.graphql.deliveryShare2.sample.AboutChat.ChatRepository;
import com.graphql.deliveryShare2.sample.AboutChat.ChatEntity;
@Component
public class ReportDataFetcher {
    @Autowired
    private ReportRepository reportRepository;
  
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ReportDataFetcher(ReportRepository reportRepository, ChatRepository chatRepository, UserRepository userRepository){
      this.reportRepository = reportRepository;
      this.chatRepository = chatRepository;
      this.userRepository = userRepository;
    }

    public DataFetcher<?> allReports () {
      return environment -> {
        return reportRepository.findAll();
      };
    }
  
    public DataFetcher<?> Report () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return reportRepository.findBySeq(seq);
      };
    }
    
    public DataFetcher<?> reportUser(){
      return environment -> {
        String userId = environment.getArgument("userId"); 
        int chatseq = environment.getArgument("chatSeq"); //chat_seq
        String content = environment.getArgument("content");
        String reason = environment.getArgument("reason");
        
        ReportEntity report = new ReportEntity(); 
        //신고 대상 유저
        UserEntity reported = userRepository.findByID(userId);
        ChatEntity chat = chatRepository.findBySeq(chatseq);
        
        System.out.println("챗방 번호"+chat.getSeq());

        report.setReason(reason);
        report.setContent(content);
        report.setReported_seq(reported.getSeq());

        if(chat.getParticipants1() == reported){
          report.setReporter_seq(chat.getParticipants2().getSeq());
          chat.setParticipant2Null();
        }
        else{
          report.setReporter_seq(chat.getParticipants1().getSeq());
          chat.setParticipant1Null();
        }

        report.setChatSeq(chatseq);
        report.setCreatedAt();
        report.setIsSolved(false);
        
        reportRepository.save(report);
        
        chat.setIsActive();

        chatRepository.save(chat);
        return report;
    };
  }
}
