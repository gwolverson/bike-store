import React from "react";
import { Alert } from "react-bootstrap";

const AlertComponent = ({ variant, message }) => (
  <Alert variant={variant}>
    {message}
  </Alert>
);

export default AlertComponent;
