import ReviewWrapper from '@components/ReviewWrapper';
import React, { VFC } from 'react';
import { Container } from './styles';
import { useQuery } from '@apollo/client';
import { GET_RES_REVIEWS } from '@Apollo/quries';
import Spinner from '@utils/Spinner';

const Reviews: VFC = () => {
  const { loading, data } = useQuery(GET_RES_REVIEWS);

  return (
    <Container>
      {loading ? <Spinner /> : <ReviewWrapper reviews={data?.allResReviews} />}
    </Container>
  );
};

export default Reviews;
