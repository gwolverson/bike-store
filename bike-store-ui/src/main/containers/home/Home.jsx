import React from "react";
import { Jumbotron, Container, Row, Col } from "react-bootstrap";
import CardComponent from "../../components/common/CardComponent";

const Home = () => (
  <div>
    <Jumbotron>
      <Container>
        <h1>Welcome to the Bike Store!</h1>
        <p>Find everything you need relating to the world of biking!</p>
      </Container>
    </Jumbotron>

    <Row>
      <Col>
        <CardComponent
          title="Bikes"
          text="Check out the awesome bikes we've got in store!"
          link="/bikes"
          buttonText="Bikes"
        />
      </Col>
      <Col>
        <CardComponent
          title="All Products"
          text="Check out all the awesome products we've got in store!"
          link="/products"
          buttonText="Products"
        />
      </Col>
      <Col>
        <CardComponent
          title="Bike Blog"
          text="Check out our bike blog, with tips, tricks and product reviews!"
          link="/blog"
          buttonText="Blog"
        />
      </Col>
    </Row>
  </div>
);

export default Home;
