import React from "react";
import { Form, Col, Button } from "react-bootstrap";

const OrderForm = ({ placeOrder }) => (
  <div>
    <Form>
      <Form.Group controlId="name">
        <Form.Label>Name</Form.Label>
        <Form.Control type="text" placeholder="Name" />
      </Form.Group>
      <Form.Group controlId="emailaddress">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="name@example.com" />
      </Form.Group>     
      <Form.Group controlId="addressline1">
        <Form.Label>Address Line 1</Form.Label>
        <Form.Control type="text" />
      </Form.Group>
      <Form.Group controlId="addressline2">
        <Form.Label>Address Line 2</Form.Label>
        <Form.Control type="text" />
      </Form.Group>
      <Form.Group controlId="towncity">
        <Form.Label>Town / City</Form.Label>
        <Form.Control type="text" />
      </Form.Group>
      <Form.Group controlId="postcode">
        <Form.Label>Postcode</Form.Label>
        <Form.Control type="text" />
      </Form.Group>
      <Form.Row>
        <Form.Group as={Col} controlId="cardnumber">
          <Form.Label>Card Number</Form.Label>
          <Form.Control type="number" />
        </Form.Group>
        <Form.Group as={Col} controlId="cardexpiry">
          <Form.Label>Card Expiry</Form.Label>
          <Form.Control type="text" />
        </Form.Group>
        <Form.Group as={Col} controlId="cardcvc">
          <Form.Label>Card CVC</Form.Label>
          <Form.Control type="number" />
        </Form.Group>
      </Form.Row>
    </Form>
    <Button variant="primary" type="submit" onClick={() => placeOrder()}>
      Place Order
    </Button>
  </div>
);

export default OrderForm;
