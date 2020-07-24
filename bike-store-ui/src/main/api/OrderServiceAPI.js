import axios from "axios";

export const createOrder = (bikeId, amount, customerId, callback) => {
  axios({
    url: "http://localhost:8081/graphql",
    method: "POST",
    data: {
      query: `
        mutation {          
          createOrder(
            bikeId: ${bikeId},
            amount: ${amount}, 
            customerId: ${customerId}
          ) {
            id
          }         
        }
      `
    }
  })
    .then(res => res.data.data.createOrder.id)
    .then(callback);
};
