package com.graphql.deliveryShare2.sample.AboutCall;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.graphql.deliveryShare2.sample.AboutCall.CallingEntity;
import com.graphql.deliveryShare2.sample.AboutCall.CallingRepository;
import com.graphql.deliveryShare2.sample.AboutCart.*;
import com.graphql.deliveryShare2.sample.AboutRestaurant.*;
import com.graphql.deliveryShare2.sample.AboutUser.UserRepository;
import com.graphql.deliveryShare2.sample.AboutUser.UserEntity;


import graphql.schema.DataFetcher;

@Component
public class OrderDataFetcher {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private UserRepository userRepository;

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
    public OrderDataFetcher(OrderRepository orderRepository, CallingRepository callingRepository, UserRepository userRepository, CartRepository cartRepository, MenuRepository menuRepository, SelectedOptionRepository selectedOptionRepository, OptionItemRepository optionItemRepository, OptionRepository optionRepository, SelectedMenuRepository selectedMenuRepository ){
      this.callingRepository=callingRepository;
      this.orderRepository=orderRepository;
      this.userRepository=userRepository;
      this.cartRepository=cartRepository;
      this.menuRepository = menuRepository;
      this.selectedOptionRepository = selectedOptionRepository;
      this.optionItemRepository = optionItemRepository;
      this.selectedMenuRepository=selectedMenuRepository;
      this.optionRepository=optionRepository;

    }
  
    public DataFetcher<?> allOrders () {
      return environment -> {
        return orderRepository.findAll();
      };
    }
  
    public DataFetcher<?> Order () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return orderRepository.findBySeq(seq);
      };
    }

    public CallingEntity getCall(OrderEntity orderEntity){
      return callingRepository.findBySeq(orderEntity.getCall().getSeq());
    }

    public DataFetcher<?> createOrder (){
      return environment -> {
        System.out.println("여기들어오긴들어오니");
        Long userseq=Long.valueOf(10);
        String status="Active";
        UserEntity us = userRepository.findBySeq(userseq);
        if (!us.getStatus().equals(status)){
            return false;
        }
        System.out.println("여기들어오긴들어오니");
        int callseq = environment.getArgument("callSeq");
        int price = callingRepository.findBySeq(callseq).getCart().get(0).getTotal_cost();
        String requestToR = environment.getArgument("requestToRestaurant");
        
        //cart에 저장
        String json_str = environment.getArgument("cart");
        JSONObject jObject = new JSONObject(json_str);
        
        CartEntity Cart = new CartEntity();
        Cart.setUserSeq(14);
        Cart.setCallseq(callseq);
        Cart.setRequest(requestToR);
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
          Cart.setTotalCost(totalmenuprice);
          cartRepository.save(Cart);
          selectedMenuRepository.save(SE);
          int selected_menukey = SE.getSeq();
 
          JSONArray jArray2 = obj.getJSONArray("options");
          for (int j=0; j<jArray2.length();j++){
            JSONObject obj2 = jArray2.getJSONObject(j);
            String category= obj2.getString("category");
            int option_seq = optionRepository.findByMenuSeqAndCategory(menuId,category).getSeq();
            JSONArray jitems = obj2.getJSONArray("items");
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
        OrderEntity Order = new OrderEntity();
        Order.setStatus("pending");
        Order.setCanWriteResReview0();
        Order.setCanWriteUserReview0();
        Order.setCall_seq(callseq);
        Order.setCreated_at();
        Order.setSum(price);
        Order.setSum(Cart.getTotal_cost());
        orderRepository.save(Order);

        return true;
      };
  }


  public DataFetcher<?> checkOrder (){
    return environment -> {
      int order_seq = environment.getArgument("seq");
      int delivery_time = environment.getArgument("deliveryTime");
      Boolean complete = environment.getArgument("isApproved");
      OrderEntity Order = orderRepository.findBySeq(order_seq);
      if (complete==true){
        Order.setStatus("completed");
        Order.setCanWriteResReview1();
        Order.setCanWriteUserReview1();
        Order.setDelivery_time(delivery_time);
        try {
          URL url = new URL ("https://exp.host/--/api/v2/push/send");
          HttpURLConnection con = (HttpURLConnection)url.openConnection();
          con.setRequestMethod("POST");
          con.setRequestProperty("host", "exp.host");
          con.setRequestProperty("content-Type", "application/json");
          con.setRequestProperty("accept", "application/json");
          con.setRequestProperty("accept-encoding", "gzip, deflate");
          con.setDoOutput(true);
          String jsonInputString = "{\"to\":\"ExponentPushToken[usertoken]\",\"body\": \"주문이 승인되었습니다. 배달예상시간은 "+delivery_time+"분입니다.\"}";
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
      else{
        Order.setStatus("cancled");
        try {
          URL url = new URL ("https://exp.host/--/api/v2/push/send");
          HttpURLConnection con = (HttpURLConnection)url.openConnection();
          con.setRequestMethod("POST");
          con.setRequestProperty("host", "exp.host");
          con.setRequestProperty("content-Type", "application/json");
          con.setRequestProperty("accept", "application/json");
          con.setRequestProperty("accept-encoding", "gzip, deflate");
          con.setDoOutput(true);
          String jsonInputString = "{\"to\":\"ExponentPushToken[usertoken]\",\"body\": \"주문이 취소되었습니다.\"}";
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
      
      orderRepository.save(Order);
      return Order;
    };

  }
  
}
