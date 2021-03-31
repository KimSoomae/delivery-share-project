import Review from '@components/Review';
import React, { VFC } from 'react';
import { Container } from './styles';

const Reviews: VFC = () => {
  return (
    <Container>
      <Review />
    </Container>
  );
};

export default Reviews;
