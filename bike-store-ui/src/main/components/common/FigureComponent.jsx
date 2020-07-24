import React from "react";
import { Figure } from "react-bootstrap";

const FigureComponent = ({imgSrc, caption}) => (
  <Figure>
    <Figure.Image
      rounded
      width={171}
      height={180}
      alt="171x180"
      src={imgSrc}
    />
    <Figure.Caption>
      {caption}
    </Figure.Caption>
  </Figure>
);

export default FigureComponent;
