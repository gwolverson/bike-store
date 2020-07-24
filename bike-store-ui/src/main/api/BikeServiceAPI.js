import axios from "axios";

export const getBikes = callback => {
  axios({
    url: 'http://localhost:8080/graphql',
    method: 'POST',
    data: {
        query: `
            {bikes {
                id
                make
                model
                description
            }}
        `
    }
  }).then(res => res.data.data.bikes)
  .then(callback)
};

export const getBikeById = (id, callback) => {
  axios({
    url: 'http://localhost:8080/graphql',
    method: 'POST',
    data: {
        query: `
            {bikeById(id: ${id}) {
                id
                make
                model
                description
                price
                reviews {
                  id
                  text
                  rating
                  user {
                    id
                    firstName
                    lastName
                  }
                }
                specification {
                  groupset
                  wheelset
                  frameset
                  tyres
                  brakes
                }
            }}
        `
    }
  }).then(res => res.data.data.bikeById)
  .then(callback)
};
