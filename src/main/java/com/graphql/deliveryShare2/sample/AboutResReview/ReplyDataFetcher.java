package com.graphql.deliveryShare2.sample.AboutResReview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ReplyDataFetcher {
    @Autowired
    private ReplyRepository replyRepository;
  
    public DataFetcher<?> allReplies () {
      return environment -> {
        return replyRepository.findAll();
      };
    }
  
    public DataFetcher<?> Reply () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return replyRepository.findBySeq(seq);
      };
    }

    public DataFetcher<?> writeReply () {
      return environment -> {
        ReplyEntity replyEntity = new ReplyEntity();
        int resReviewSeq = environment.getArgument("resReviewSeq");
        String content = environment.getArgument("content");
        replyEntity.setContent(content);
        replyEntity.setReviewSeq(resReviewSeq);
        replyEntity.setCreatedAt();

        replyRepository.save(replyEntity);

        try {
          URL url = new URL ("https://exp.host/--/api/v2/push/send");
          HttpURLConnection con = (HttpURLConnection)url.openConnection();
          con.setRequestMethod("POST");
          con.setRequestProperty("host", "exp.host");
          con.setRequestProperty("content-Type", "application/json");
          con.setRequestProperty("accept", "application/json");
          con.setRequestProperty("accept-encoding", "gzip, deflate");
          con.setDoOutput(true);
          String jsonInputString = "{\"to\": \"ExponentPushToken[usertoken]\",\"body\": \"작성하신 리뷰에 사장님 댓글이 달렸어요~\"}";
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
        return true;
      };
    }

    public DataFetcher<?> updateReply () {
      return environment -> {
        int replySeq = environment.getArgument("replySeq");
        ReplyEntity replyEntity = replyRepository.findBySeq(replySeq);
        String content = environment.getArgument("content");
        replyEntity.setContent(content);
        replyEntity.setUpdatedAt();

        replyRepository.save(replyEntity);
        return replyEntity;
      };
    }


    
  
}
