import Order from '@components/Order';
import OrderModal from '@components/OrderModal';
import React, { useState, VFC } from 'react';
import { Container } from './styles';
import { useQuery } from '@apollo/client';
import { GET_ORDERS } from '@Apollo/quries';
import Spinner from '@utils/Spinner';

const Orders: VFC = () => {
  const [showOrderModal, setShowOrderModal] = useState(false);
  const [orderData, setOrderInfo] = useState('');
  const { loading, data } = useQuery(GET_ORDERS);

  return (
    <Container>
      {loading ? (
        <Spinner />
      ) : (
        <>
          <Order
            orders={data?.allOrders}
            setModalInfo={setOrderInfo}
            setShowModal={setShowOrderModal}
          />
          <OrderModal
            show={showOrderModal}
            data={orderData}
            setShowModal={setShowOrderModal}
          />
        </>
      )}
    </Container>
  );
};

export default Orders;
