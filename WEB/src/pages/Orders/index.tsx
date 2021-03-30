import Order from '@components/Order';
import React, { VFC } from 'react';
import { Container } from './styles';

interface Props {
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: string) => void;
}

const Orders: VFC<Props> = ({ setShowModal, setModalInfo }) => {
  return (
    <Container>
      <Order setModalInfo={setModalInfo} setShowModal={setShowModal} />
    </Container>
  );
};

export default Orders;
