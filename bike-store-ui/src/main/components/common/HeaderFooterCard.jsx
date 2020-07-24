import React from "react";
import { Card } from "react-bootstrap";

const HeaderFooterCard = ({ header, footer, content }) => (
  <Card>
    <Card.Header>{header}</Card.Header>
    <Card.Body>
      <blockquote className="blockquote mb-0">
        <p> {content} </p>
        <footer className="blockquote-footer">{footer}</footer>
      </blockquote>
    </Card.Body>
  </Card>
);

export default HeaderFooterCard;
