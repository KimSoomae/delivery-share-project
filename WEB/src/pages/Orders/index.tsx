import Order from '@components/Order';
import OrderModal from '@components/OrderModal';
import React, { useEffect, useState, VFC } from 'react';
import { Container } from './styles';
import { useQuery } from '@apollo/client';
import { GET_ORDERS } from '@Apollo/quries';
import Spinner from '@utils/Spinner';
import { PropsOrder } from '@utils/type';

const Orders: VFC = () => {
  const [showOrderModal, setShowOrderModal] = useState(false);
  const [orderData, setOrderInfo] = useState<PropsOrder>();
  const { loading, data, refetch } = useQuery(GET_ORDERS);
  const [polling, setPolling] = useState(false);

  useEffect(() => {
    const timer = setTimeout(async () => {
      await refetch();
      setPolling(prev => !prev);
    }, 3000);
    return () => clearInterval(timer);
  }, [polling]);

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
