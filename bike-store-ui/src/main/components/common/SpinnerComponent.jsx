import React from "react";
import { Spinner } from "react-bootstrap";

const SpinnerComponent = ({ text }) => (
  <Spinner animation="border" role="status">
    <span className="sr-only">{text}</span>
  </Spinner>
);

export default SpinnerComponent;
