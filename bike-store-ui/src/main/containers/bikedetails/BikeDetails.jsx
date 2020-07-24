import React, { useState, useEffect } from "react";
import Title from "../../components/layout/Title";
import { getBikeById } from "../../api/BikeServiceAPI";
import FigureComponent from "../../components/common/FigureComponent";
import stockBike from "../../assets/images/stock-bike.jpg";
import { Row, Col, Tab, Nav } from "react-bootstrap";
import CardComponent from "../../components/common/CardComponent";
import HeaderFooterCard from "../../components/common/HeaderFooterCard";

const BikeDetails = ({ match }) => {
  const [bike, setBike] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const bikeId = match.params.id;
    setLoading(true);
    getBikeById(bikeId, (bike) => {
      console.log(bike);
      setBike(bike);
      setLoading(false);
    });
  }, [match.params.id]);

  const createReviewsContent = () => {
    return (
      bike.reviews &&
      bike.reviews.map((review) => {
        return (
          <HeaderFooterCard
            key={review.id}
            header={`Rating: ${review.rating}/5`}
            content={review.text}
            footer={`${review.user.firstName} ${review.user.lastName}`}
          />
        );
      })
    );
  };

  const createSpecificationList = () => {      
    return Object.keys(bike.specification).map(key => {
        return <li key={key}>{key}: {bike.specification[key]}</li>
    })
  }

  return (
    <div>
      {loading && <h1>Loading Details...</h1>}
      {!loading && bike && (
        <div>
          <Title text={`${bike.make} - ${bike.model}`} />
          <Row>
            <Col>
              <FigureComponent caption={bike.description} imgSrc={stockBike} />
            </Col>
            <Col>
              <CardComponent
                title="Buy It!"
                text={`Price: £${bike.price}`}
                link={{pathname: '/order', state: { bikeId: bike.id, bikeName: `${bike.make} - ${bike.model}`, price: bike.price}}}
                buttonText="Buy Now"
              />
            </Col>
          </Row>
          <Tab.Container id="left-tabs-example" defaultActiveKey="reviews">
            <Row>
              <Col sm={2}>
                <Nav variant="pills" className="flex-column">
                  <Nav.Item>
                    <Nav.Link eventKey="reviews">Reviews</Nav.Link>
                  </Nav.Item>
                  <Nav.Item>
                    <Nav.Link eventKey="specification">Specification</Nav.Link>
                  </Nav.Item>
                </Nav>
              </Col>
              <Col sm={10}>
                <Tab.Content>
                  <Tab.Pane eventKey="reviews" className="tab-container">
                    {createReviewsContent()}
                  </Tab.Pane>
                  <Tab.Pane eventKey="specification">
                    <HeaderFooterCard                      
                      header="Specification"
                      content={createSpecificationList()}
                      footer={`${bike.make} - ${bike.model}`}
                    />
                  </Tab.Pane>
                </Tab.Content>
              </Col>
            </Row>
          </Tab.Container>
        </div>
      )}
    </div>
  );
};

export default BikeDetails;
