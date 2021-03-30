import loadable from '@loadable/component';
import React, { ReactElement } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

const Dashboard = loadable(() => import('@layouts/Dashboard'));

function App(): ReactElement {
  return (
    <Switch>
      <Redirect exact path="/" to="/dashboard/order" />
      <Route path="/dashboard" component={Dashboard} />
    </Switch>
  );
}

export default App;
