import React, { VFC } from 'react';
import { LazyLoadImage } from 'react-lazy-load-image-component';
import { Content, ListWrapper, MapImage, SubTitle } from './styles';
import { useQuery } from '@apollo/client';
import { GET_RESTAURANT } from '@Apollo/quries';

const Info: VFC = () => {
  // const { loading, data } = useQuery(GET_RESTAURANT);

  // console.log(loading, data);

  return (
    <>
      <MapImage>
        <LazyLoadImage src="../images/map.PNG" alt="restaurant-location-image" />
      </MapImage>
      <ListWrapper>
        <SubTitle>위치</SubTitle>
        <Content>서울특별시 동대문구 이문1동 300</Content>
      </ListWrapper>

      <ListWrapper>
        <SubTitle>소개</SubTitle>
        <Content>이문동 최고의 맛집입니다!</Content>
      </ListWrapper>

      <ListWrapper>
        <SubTitle>운영시간</SubTitle>
        <Content>14:00PM - 24:00AM</Content>
      </ListWrapper>
    </>
  );
};

export default Info;
