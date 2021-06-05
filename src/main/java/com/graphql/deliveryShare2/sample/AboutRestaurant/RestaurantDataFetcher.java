package com.graphql.deliveryShare2.sample.AboutRestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.graphql.deliveryShare2.sample.AboutResReview.ResReviewEntity;
import com.graphql.deliveryShare2.sample.AboutResReview.ResReviewRepository;

import graphql.schema.DataFetcher;
import java.util.List;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.Arrays;
@Component
public class RestaurantDataFetcher {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RunTimeRepository runTimeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private DeliverylocRepository deliverylocRepository;

    @Autowired
    private ResReviewRepository resReviewRepository;

    @Autowired
    public RestaurantDataFetcher(RestaurantRepository restaurantRepository, RunTimeRepository runTimeRepository, MenuRepository menuRepository, OptionRepository optionRepository, DeliverylocRepository deliverylocRepository, ResReviewRepository resReviewRepository){
      this.restaurantRepository=restaurantRepository;
      this.runTimeRepository=runTimeRepository;
      this.menuRepository = menuRepository;
      this.optionRepository=optionRepository;
      this.deliverylocRepository=deliverylocRepository;
      this.resReviewRepository=resReviewRepository;
    }

    public RunTimeEntity getRunTime(RestaurantEntity restaurantEntity){
      return runTimeRepository.findBySeq(restaurantEntity.getRunTime().getSeq());

    }


    public List<OptionEntity> getOptions(RestaurantEntity restaurantEntity) {
      return optionRepository.findAll();
      

    }
    public MenuEntity getMenu(RestaurantEntity restaurantEntity) {
      return menuRepository.findBySeq(restaurantEntity.getMenu().getSeq());
    }


    public DataFetcher<?> allRestaurants () {
      return environment -> {
        return restaurantRepository.findAll();
      };
    }
  
    public DataFetcher<?> getRestaurants () {
      return environment -> {
        String category = environment.getArgument("category");
        String si = environment.getArgument("si");
        String dong = environment.getArgument("dong");
        List<RestaurantEntity> res =new ArrayList<RestaurantEntity>();
        List<DeliverylocEntity> de = deliverylocRepository.findAllBySiAndDong(si,dong);
        System.out.println("여기 주목!!"+de);
        for (int i=0; i<de.size();i++){
          int idx = de.get(i).getResseq();
          RestaurantEntity re = restaurantRepository.findBySeq(idx);
          System.out.println("여기 주목3!!"+re.getCategory());
          System.out.println("카테고리는!!"+category);
          System.out.println("이스오픈은!!"+re.getIsopen());
          System.out.println(re.getCategory()==category);
          System.out.println(re.getIsopen()==1);
          if((Objects.equals(re.getCategory(),category))&&(re.getIsopen()==1)){
            System.out.println("여기 들어왔따");
            res.add(re);
            //res.set(i,re);
          }
        }
        System.out.println("여기 주목2!!"+res);
        for (int i=0; i<res.size();i++){
          RestaurantEntity re= res.get(i);
          
          List<MenuEntity> menus=new ArrayList<MenuEntity>();
          for (int j=0; j<re.getMenus().size();j++){
              if (re.getMenus().get(j).getIsBestmenu()==true){
                  menus.add(re.getMenus().get(j));
              }
          }
          re.setBestmenu(menus);
          re.setReviewcount(re.getReviews().size());
          
      }
        return res;
      };
    }

    public DataFetcher<?> getRestaurant () {
      return environment -> {
        int seq = environment.getArgument("seq");
        RestaurantEntity re = restaurantRepository.findBySeq(seq);
        List<String> obj=Arrays.asList(re.getDayoff().split(","));
        String json = new Gson().toJson(obj);
        
        re.setDayoff(json);
        re.setSeperatable(false);
        for (int i=0; i<re.getMenus().size();i++){
            Boolean flag = re.getMenus().get(i).getIsSeperatable();
            if (flag==true){
                re.setSeperatable(true);
                break;
            }
        }
        int likecnt = re.getLikes().size();
        re.setLikescount(likecnt);
        re.setIsliked(false);
        for (int i=0; i<likecnt; i++){
            LikesEntity le = re.getLikes().get(i);
            if (le.getUserseq()==10){
                re.setIsliked(true);
           }
        }
        List<MenuEntity> menus=new ArrayList<MenuEntity>();
          for (int j=0; j<re.getMenus().size();j++){
              if (re.getMenus().get(j).getIsBestmenu()==true){
                  menus.add(re.getMenus().get(j));
              }
          }
        re.setBestmenu(menus);
        for (int i=0; i<re.getReviews().size();i++){
        
          ResReviewEntity rre= re.getReviews().get(i);
          
          double rate=rre.getRate();
          if (rate==5.0){
              int cnt = re.getRate5count();
              cnt++;
              re.setRate5count(cnt);
          }
          else if ((rate<5.0)&&(rate>=4.0)){
              int cnt = re.getRate4count();
              cnt++;
              re.setRate4count(cnt);
          }
          else if ((rate<4.0)&&(rate>=3.0)){
              int cnt = re.getRate3count();
              cnt++;
              re.setRate3count(cnt);
          }
          else if ((rate<3.0)&&(rate>=2.0)){
              int cnt = re.getRate2count();
              cnt++;
              re.setRate2count(cnt);
          }
          else if ((rate<2.0)&&(rate>=1.0)){
              int cnt = re.getRate1count();
              cnt++;
              re.setRate1count(cnt);
          }
         
          
      }
        return re; 
      };
      
    }

    public DataFetcher<?> getLikedRestaurants() {
      return environment -> {
        int userseq =10;
        List<RestaurantEntity> likedR = new ArrayList<RestaurantEntity>();
        List<LikesEntity> likes = likesRepository.findAllByUserseq(userseq);
        System.out.println("라이크"+likes);
        for (int i=0; i<likes.size();i++){
            int idx = likes.get(i).getResseq();
            System.out.println("인덱스"+idx);
            System.out.println("레스"+restaurantRepository.findBySeq(idx));
            likedR.add(restaurantRepository.findBySeq(idx));
        }
  
        return likedR;
      };
    }

    public DataFetcher<?> searchRestaurant(){
      return environment -> {
        String keyword = environment.getArgument("keyword");
        System.out.println("키워드" + keyword);
        //식당이름에 키워드 포함 레스토랑들
        List<RestaurantEntity> resBymenu = restaurantRepository.findAllByNameContaining(keyword);
        List<MenuEntity> menuBykey = menuRepository.findAllByNameContaining(keyword);
        //결과 리스트
        List<RestaurantEntity> resultRes = new ArrayList<RestaurantEntity>();

        //위치(si, dong)에 맞는 레스토랑 리스트들
        String si = environment.getArgument("si");
        String dong = environment.getArgument("dong");
        List<DeliverylocEntity> deloc = deliverylocRepository.findAllBySiAndDong(si,dong);
        List<RestaurantEntity> resByloc = new ArrayList<RestaurantEntity>(); 
        
        for(int i = 0; i< deloc.size();i++){
          int idx = deloc.get(i).getResseq();
          RestaurantEntity reloc = restaurantRepository.findBySeq(idx);
          if(reloc.getIsopen() == 1){
            resByloc.add(reloc);
          }
        }
        //배달가능한 식당들 중 이름에 keyword 포함
        for(int i = 0; i<resBymenu.size(); i++){
          int idx = resBymenu.get(i).getSeq();
          RestaurantEntity resb = restaurantRepository.findBySeq(idx);

          if(resultRes.contains(resb)){
            i++;
            continue;
          }
          else if(resByloc.contains(resb) && (resb.getIsopen() == 1)){
            resultRes.add(resb);
          }
        }
        //메뉴에 keyword 포함 레스토랑들
        for(int i = 0; i< menuBykey.size(); i++){
          int idx = menuBykey.get(i).getResseq();

          System.out.println("레스"+restaurantRepository.findBySeq(idx));
          RestaurantEntity resbb = restaurantRepository.findBySeq(idx);
          
          if (resultRes.contains(resbb)){
            i++;
            continue;
          }
          else if((resbb.getIsopen() == 1) && resByloc.contains(resbb)){
            resultRes.add(resbb);
          }
        }
        
        return resultRes;
      };
    }
   

    public DataFetcher<?> getResReviews () {
      return environment -> {
        int resseq = environment.getArgument("resseq");
        return restaurantRepository.findBySeq(resseq);
      };
    }
   
 
}