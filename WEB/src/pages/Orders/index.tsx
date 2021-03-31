import Order from '@components/Order';
import OrderModal from '@components/OrderModal';
import React, { useState, VFC } from 'react';
import { Container } from './styles';

const Orders: VFC = () => {
  const [showOrderModal, setShowOrderModal] = useState(false);
  const [orderData, setOrderInfo] = useState('');

  return (
    <Container>
      <Order setModalInfo={setOrderInfo} setShowModal={setShowOrderModal} />
      <OrderModal
        show={showOrderModal}
        data={orderData}
        setShowModal={setShowOrderModal}
      />
    </Container>
  );
};

export default Orders;
