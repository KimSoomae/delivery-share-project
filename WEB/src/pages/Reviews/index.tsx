import Review from '@components/Review';
import React, { VFC } from 'react';
import { Container } from './styles';

interface Props {
  setShowModal: (flag: boolean) => void;
}

const Reviews: VFC<Props> = ({ setShowModal }) => {
  return (
    <Container>
      <Review setShowModal={setShowModal} />
    </Container>
  );
};

export default Reviews;
