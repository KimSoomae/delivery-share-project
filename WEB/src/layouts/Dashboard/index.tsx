import NavList from '@components/NavList';
import React, { useCallback, useState, VFC } from 'react';
import { Route, Switch } from 'react-router-dom';
import { Container, Mainboard, Navbar } from './styles';
import loadable from '@loadable/component';
import OrderModal from '@components/OrderModal';
import MenuModal from '@components/MenuModal';
import ReviewModal from '@components/ReviewModal';

const Orders = loadable(() => import('@pages/Orders/index'));
const Menus = loadable(() => import('@pages/Menus/index'));
const Reviews = loadable(() => import('@pages/Reviews/index'));
const Infos = loadable(() => import('@pages/Infos/index'));

const Dashboard: VFC = () => {
  const [showOrderModal, setShowOrderModal] = useState(false);
  const [orderInfo, setOrderInfo] = useState('');
  const [showMenuModal, setShowMenuModal] = useState(false);
  const [menuInfo, setMenuInfo] = useState('');
  const [showReviewModal, setShowReviewModal] = useState(false);

  const onCloseModal = useCallback(() => {
    setShowOrderModal(false);
    setShowMenuModal(false);
    setShowReviewModal(false);
  }, []);

  return (
    <Container>
      <Navbar>
        <NavList />
      </Navbar>

      <Mainboard>
        <Switch>
          <Route
            path="/dashboard/order"
            render={() => (
              <Orders setShowModal={setShowOrderModal} setModalInfo={setOrderInfo} />
            )}
          />
          <Route
            path="/dashboard/menu"
            render={() => (
              <Menus setShowModal={setShowMenuModal} setModalInfo={setMenuInfo} />
            )}
          />
          <Route
            path="/dashboard/review"
            render={() => <Reviews setShowModal={setShowReviewModal} />}
          />
          <Route path="/dashboard/info" component={Infos} />
        </Switch>
      </Mainboard>

      <OrderModal
        show={showOrderModal}
        info={orderInfo}
        onCloseModal={onCloseModal}
        setShowModal={setShowOrderModal}
      />
      <MenuModal
        show={showMenuModal}
        info={menuInfo}
        onCloseModal={onCloseModal}
        setShowModal={setShowMenuModal}
      />
      <ReviewModal
        show={showReviewModal}
        onCloseModal={onCloseModal}
        setShowModal={setShowReviewModal}
      />
    </Container>
  );
};

export default Dashboard;
