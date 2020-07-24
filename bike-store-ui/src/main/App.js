import React from "react";
import { Switch, Route } from "react-router-dom";
import Home from "./containers/home/Home";
import Bikes from "./containers/bikes/Bikes";
import BikeDetails from './containers/bikedetails/BikeDetails'
import Order from "./containers/order/Order";

const App = () => (
  <Switch>
    <Route exact path="/">
      <Home />
    </Route>
    <Route path="/bikes" component={Bikes} />
    <Route path="/bike-details/:id" render={props => <BikeDetails {...props}/>} />
    <Route path="/order" render={props => <Order {...props}/>} />
    <Route path="/products" component={() => <h1>Products</h1>} />
    <Route path="/blog" component={() => <h1>Blog</h1>} />
  </Switch>
);

export default App;
