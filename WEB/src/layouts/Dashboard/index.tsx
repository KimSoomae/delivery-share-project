import NavList from '@components/NavList';
import React, { VFC } from 'react';
import { Route, Switch } from 'react-router-dom';
import { Container, Mainboard, Navbar } from './styles';
import loadable from '@loadable/component';

const Orders = loadable(() => import('@pages/Orders/index'));
const Menus = loadable(() => import('@pages/Menus/index'));
const Reviews = loadable(() => import('@pages/Reviews/index'));
const Infos = loadable(() => import('@pages/Infos/index'));

const Dashboard: VFC = () => {
  return (
    <Container>
      <Navbar>
        <NavList />
      </Navbar>

      <Mainboard>
        <Switch>
          <Route path="/dashboard/order" component={Orders} />
          <Route path="/dashboard/review" component={Reviews} />
          <Route path="/dashboard/menu" component={Menus} />
          <Route path="/dashboard/info" component={Infos} />
        </Switch>
      </Mainboard>
    </Container>
  );
};

export default Dashboard;
