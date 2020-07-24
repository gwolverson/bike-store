import React, { useEffect, useState } from "react";
import Title from "../../components/layout/Title";
import { Row, Col } from "react-bootstrap";
import CardComponent from "../../components/common/CardComponent";
import { getBikes } from "../../api/BikeServiceAPI";
import "./styles.css"

const Bikes = () => {
  const [bikes, setBikes] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    getBikes(bikes => {
      setBikes(bikes);
      setLoading(false);
    });    
  }, []);

  const createBikeRowsFromData = () => {
    let bikeCounter = 0;
    let colCounter = 0;    
    let rowCounter = 0;    
    let rows = [];
    let cols = [];
    while (bikeCounter < bikes.length) {
      if (colCounter < 3) {
        cols[colCounter] = bikes[bikeCounter];
        colCounter++;
        bikeCounter++;
      } else {
        rows[rowCounter] = cols;
        rowCounter++;
        cols = [];
        colCounter = 0;
      }
    }

    if(cols !== []) {
      rows[rowCounter] = cols;
    }

    return rows.map((row, index) => {
      return (
        <Row key={index} className="bikes-row">
          {row.map((col, index) => {
            return (
              <Col key={index}>
                <CardComponent
                  title={`${col.make} - ${col.model}`}
                  text={col.description}
                  link={`/bike-details/${col.id}`}
                  buttonText="Details"
                />
              </Col>
            );
          })}
        </Row>
      );
    });
  };

  return (
    <div>
      <Title text="Bikes" />
      {loading && <h2>Loading Data...</h2>}
      {!loading && bikes.length > 0 && createBikeRowsFromData()}
    </div>
  );
};

export default Bikes;
