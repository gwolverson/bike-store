import React from "react";
import {withRouter} from 'react-router-dom';
import { Card, Button } from "react-bootstrap";

const CardComponent = ({title, text, buttonText, link, history, imgSrc}) => (
  <Card style={{ width: "18rem" }}>
    {imgSrc && <Card.Img variant="top" src={imgSrc} />}
    <Card.Body>
      <Card.Title>{title}</Card.Title>
      <Card.Text>{text}</Card.Text>
      <Button variant="primary" onClick={() => history.push(link)}>{buttonText}</Button>
    </Card.Body>
  </Card>
);

export default withRouter(CardComponent);
