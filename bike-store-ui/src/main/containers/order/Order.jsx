import React from "react";
import { Row, Col } from "react-bootstrap";
import Title from "../../components/layout/Title";
import FigureComponent from "../../components/common/FigureComponent";
import stockBike from "../../assets/images/stock-bike.jpg";
import { useState } from "react";
import OrderForm from "../../components/forms/OrderForm";
import SpinnerComponent from '../../components/common/SpinnerComponent';
import AlertComponent from '../../components/common/AlertComponent';
import {createOrder} from '../../api/OrderServiceAPI';

const Order = ({ location }) => {
  const { bikeId, bikeName, price } = location.state;
  const [ordering, setOrdering] = useState(false);
  const [orderResult, setOrderResult] = useState(null);

  const placeOrder = () => {
    setOrdering(true);
    createOrder(bikeId, price, 1, response => {
      setOrdering(false);
      if(response !== null || response !== undefined) {
        setOrderResult(response);
      } else {
        setOrderResult(false);
      }
    });
  };

  return (
    <div>
      <Title text="Order" />
      <Row>
        <Col xs={8}>
          {!ordering && orderResult == null && <OrderForm placeOrder={placeOrder} />}
          {ordering && <SpinnerComponent text="Order In Progress"/> }
          {!ordering && orderResult != null && orderResult && <AlertComponent variant="success" message={`Order successful! Order ID: ${orderResult}`}  />}
          {!ordering && orderResult === false && <AlertComponent variant="danger" message="Order unsuccessful!" />}
        </Col>
        <Col>
          <FigureComponent
            caption={`${bikeName} : £${price}`}
            imgSrc={stockBike}
          />
        </Col>
      </Row>
    </div>
  );
};

export default Order;
