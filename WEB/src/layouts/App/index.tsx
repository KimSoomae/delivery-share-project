import loadable from '@loadable/component';
import React, { ReactElement } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import { ApolloProvider } from '@apollo/client';
import client from '@Apollo/client';

const Dashboard = loadable(() => import('@layouts/Dashboard'));

function App(): ReactElement {
  return (
    <ApolloProvider client={client}>
      <Switch>
        <Redirect exact path="/" to="/dashboard/order" />
        <Route path="/dashboard" component={Dashboard} />
      </Switch>
    </ApolloProvider>
  );
}

export default App;
