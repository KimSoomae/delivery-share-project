package com.graphql.deliveryShare2.sample.AboutCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.MenuRepository;
import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionItemEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantEntity;
import com.graphql.deliveryShare2.sample.AboutRestaurant.RestaurantRepository;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;
import com.graphql.deliveryShare2.sample.AboutUser.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import com.graphql.deliveryShare2.sample.AboutCart.CartEntity;
import com.graphql.deliveryShare2.sample.AboutCart.CartRepository;
import com.graphql.deliveryShare2.sample.AboutCart.SelectedMenuEntity;
import com.graphql.deliveryShare2.sample.AboutCart.SelectedMenuRepository;
import com.graphql.deliveryShare2.sample.AboutCart.SelectedOptionEntity;
import com.graphql.deliveryShare2.sample.AboutCart.SelectedOptionRepository;
import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionRepository;
import com.graphql.deliveryShare2.sample.AboutRestaurant.OptionItemRepository;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Exception;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import graphql.schema.DataFetcher;

import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.ExpoPushMessageTicketPair;
import io.github.jav.exposerversdk.ExpoPushReceipt;
import io.github.jav.exposerversdk.ExpoPushTicket;
import io.github.jav.exposerversdk.PushClient;
import io.github.jav.exposerversdk.PushClientException;
import kotlin.jvm.Throws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Component
public class CallingDataFetcher  {
    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CallLocationRepository callLocationRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private SelectedOptionRepository selectedOptionRepository;

    @Autowired
    private OptionItemRepository optionItemRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private SelectedMenuRepository selectedMenuRepository;
    @Autowired
    public CallingDataFetcher(CallingRepository callingRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, CallLocationRepository callLocationRepository, CartRepository cartRepository, MenuRepository menuRepository, SelectedOptionRepository selectedOptionRepository, OptionItemRepository optionItemRepository, SelectedMenuRepository selectedMenuRepository, OptionRepository optionRepository){
      this.callingRepository=callingRepository;
      this.userRepository=userRepository;
      this.restaurantRepository=restaurantRepository;
      this.callLocationRepository=callLocationRepository;
      this.cartRepository = cartRepository;
      this.menuRepository = menuRepository;
      this.selectedOptionRepository = selectedOptionRepository;
      this.optionItemRepository = optionItemRepository;
      this.selectedMenuRepository=selectedMenuRepository;
      this.optionRepository=optionRepository;
    }

    public DataFetcher<?> allCallings () {
      return environment -> {
        return callingRepository.findAll();
      };
    }
  
    public DataFetcher<?> Calling () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return callingRepository.findBySeq(seq);


      };
    }

    public DataFetcher<?> getNearCalls (){
      return environment -> {
        Double latitude = environment.getArgument("latitude");
        Double longitude = environment.getArgument("longitude");
        List <CallingEntity> CE = callingRepository.findAllByStatus("isActivated");
        List <CallingEntity> results = new ArrayList<CallingEntity>();
        for (int i=0; i<CE.size();i++){
          Double calllatitude = CE.get(i).getCallLocation().getLatitude();
          Double calllongitude =  CE.get(i).getCallLocation().getLongitude();
          double pi=3.141592653589793;
          double distance = (((Math.acos(Math.sin((latitude*pi/180)) * 
          Math.sin((calllatitude*pi/180)) + Math.cos((latitude*pi/180)) * 
          Math.cos((calllatitude*pi/180)) * Math.cos(((longitude-calllongitude) * pi/180)))) 
          * 180/pi * 60 * 1.1515 * 1.609344));
          if (distance<=0.5){
                CallingEntity call = CE.get(i);
                call.setDistance(distance*1000);
                results.add(call);
                
          }
         
        }
        return results;
      };
  }



  public DataFetcher<?> getMyCall(){
    return environment -> {
      List<CallingEntity> call = callingRepository.findAll();
      
      for (int i=0; i<call.size();i++){
        if (call.get(i).getUserSeq() == 10){
          if(call.get(i).getStatus().equals("isActivated") ){
            return call.get(i);

          }
          
        }
        
      }
      return null;
    };
}
  



    public UserEntity getUser(CallingEntity callingEntity){
      return userRepository.findBySeq(callingEntity.getUser().getSeq());
    }

    public RestaurantEntity getRestaurant(CallingEntity callingEntity){
      return restaurantRepository.findBySeq(callingEntity.getRestaurant().getSeq());
    }

    public CallLocationEntity getCallLocation(CallingEntity callingEntity){
      return callLocationRepository.findBySeq(callingEntity.getCallLocation().getSeq());
    }

    public List<CartEntity> getCart(CallingEntity callingEntity) {
      return cartRepository.findAll();
      

    }
    public DataFetcher<?> cancelCall (){
      return environment -> {
      int seq = environment.getArgument("seq");
      callingRepository.deleteBySeq(seq);
      return true;
    
      };
    }

    public DataFetcher<?> createCall (){
      return environment -> {
        Long userseq=Long.valueOf(10);
        String status="Active";
        UserEntity us = userRepository.findBySeq(userseq);
        if (!us.getStatus().equals(status)){
            return false;
        }
     
        String address = environment.getArgument("address");
        Double latitude = environment.getArgument("latitude");
        Double longitude = environment.getArgument("longitude");
      
        CallLocationEntity CE = new CallLocationEntity();
        CE.setPlace(address);
        CE.setLatitude(latitude);
        CE.setLongitude(longitude);
        callLocationRepository.save(CE);
        int callLocationkey = CE.getSeq();
        CallingEntity Call = new CallingEntity();
        int timelimit = environment.getArgument("timeLimit");
        String requestR = environment.getArgument("requestToRes");
        String requestU = environment.getArgument("requestToUser");
        Call.setCreatedAt();
        Call.setExpiredAt(timelimit);
        Call.setStatus("isActivated");
        Call.setRequestCall(requestU);
        Call.setTimelimit(timelimit);
        Call.setUserseq(userseq);
        Call.setLocationseq(callLocationkey);
        //cart에 저장
        String json_str = environment.getArgument("cart");
        JSONObject jObject = new JSONObject(json_str);
        JSONObject resObject = jObject.getJSONObject("restaurant");
        int res_id = resObject.getInt("id");
        Call.setResseq(res_id);
        if (restaurantRepository.findBySeq(res_id).getIsopen()==0){
          return false;
        }
        Call.setRestaurant(restaurantRepository.findBySeq(res_id));
        callingRepository.save(Call);
        int callkey = Call.getSeq();
        int delivery_tip = restaurantRepository.findBySeq(res_id).getDelivery_tip();
        CartEntity Cart = new CartEntity();
        Cart.setUserSeq(10);
        Cart.setDeliverytip(delivery_tip);
        Cart.setCallseq(callkey);
        Cart.setRequest(requestR);
        cartRepository.save(Cart);
        int cartkey = Cart.getSeq();
        
        JSONArray jArray = jObject.getJSONArray("menus");
        for (int i=0; i<jArray.length();i++){
          JSONObject obj = jArray.getJSONObject(i);
          JSONObject menu = obj.getJSONObject("menu");
          int menuId = menu.getInt("id");
          if (menuRepository.findBySeq(menuId).getIsAvailable()==false){
              return false;
          }
          int totalmenuprice = obj.getInt("price");
          Boolean isSeperated = obj.getBoolean("isSeperated");
          int count = obj.getInt("count");
          SelectedMenuEntity SE = new SelectedMenuEntity();
          SE.setMenuSeq(menuId);
          SE.setCount(count);
          SE.setCartseq(cartkey);
          SE.setPrice(totalmenuprice);
          SE.setIsSeperated(isSeperated);
          Call.setPrice(totalmenuprice);
          Cart.setTotalCost(totalmenuprice);
          cartRepository.save(Cart);
          callingRepository.save(Call);
          selectedMenuRepository.save(SE);
          int selected_menukey = SE.getSeq();
 
          JSONArray jArray2 = obj.getJSONArray("options");
          for (int j=0; j<jArray2.length();j++){
            JSONObject obj2 = jArray2.getJSONObject(j);
            String category= obj2.getString("category");
            int option_seq = optionRepository.findByMenuSeqAndCategory(menuId,category).getSeq();
            JSONArray jitems = obj2.getJSONArray("items");
            //String [] results = new String[10];
            for (int k=0; k<jitems.length();k++){
              String content =jitems.getString(k);
              int optionItemSeq = optionItemRepository.findByContentAndOption_seq(content, option_seq).getSeq();
              SelectedOptionEntity Sel_opt = new SelectedOptionEntity();
              Sel_opt.setOptionItemSeq(optionItemSeq);
              Sel_opt.setSelectedmenuSeq(selected_menukey);
              selectedOptionRepository.save(Sel_opt);
            }

          }
        }
        
        LocalDateTime timelimitAfter = LocalDateTime.now().plusMinutes(timelimit);
        Date timelimitAsDate = Date.from(timelimitAfter.atZone(ZoneId.systemDefault()).toInstant());
        TimerTask timerTask = new TimerTask() {
          
          @Override
          public void run() {
             
                int expired = callingRepository.deleteBySeqAndStatus(callkey,"isActivated");
                if (expired>0){
                try {
                  URL url = new URL ("https://exp.host/--/api/v2/push/send");
                  HttpURLConnection con = (HttpURLConnection)url.openConnection();
                  con.setRequestMethod("POST");
                  con.setRequestProperty("host", "exp.host");
                  con.setRequestProperty("content-Type", "application/json");
                  con.setRequestProperty("accept", "application/json");
                  con.setRequestProperty("accept-encoding", "gzip, deflate");
                  con.setDoOutput(true);
                   
                  String jsonInputString = "{\"to\": \"ExponentPushToken[usertoken]\",\"body\": "+timelimit+"분 경과로 콜이 취소되었습니다.\"}";
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
              }
                
                
          }  
        }; 
        new Timer().schedule(timerTask, timelimitAsDate);
       
          
        return Call;
      };
  }

  public DataFetcher<?> deleteCall(){
    return environment -> {
      Long userseq=Long.valueOf(10);
      List<CallingEntity> ce = callingRepository.findAllByUser_seq(userseq);
      for (int i=0; i<ce.size();i++){
        int idx=ce.get(i).getSeq();
        callingRepository.deleteBySeq(idx);
      }
      
      return true;
    };
  }

  public DataFetcher<?> updateCall(){
    return environment -> {
      int callSeq = environment.getArgument("seq");
      CallingEntity ce = callingRepository.findBySeq(callSeq);
      if (ce.getStatus().equals("isActivated")){
        ce.setStatus("isHalfPaid");
        try {
          URL url = new URL ("https://exp.host/--/api/v2/push/send");
          HttpURLConnection con = (HttpURLConnection)url.openConnection();
          con.setRequestMethod("POST");
          con.setRequestProperty("host", "exp.host");
          con.setRequestProperty("content-Type", "application/json");
          con.setRequestProperty("accept", "application/json");
          con.setRequestProperty("accept-encoding", "gzip, deflate");
          con.setDoOutput(true);
        
          String jsonInputString = "{\"to\": \"ExponentPushToken[usertoken]\",\"body\": \"콜이 매칭되었습니다! 결제를 이어서 진행해주세요.\"}";
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
      }
      else if (ce.getStatus().equals("isHalfPaid")){
        ce.setStatus("isCompleted");
      }
      callingRepository.save(ce);
      return ce;
    };
  }
  

  
}
