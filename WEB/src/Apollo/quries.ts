import { gql } from '@apollo/client';

export const GET_ORDERS = gql`
  query {
    allOrders {
      seq
      status
    }
  }
`;

export const GET_RES_REVIEWS = gql`
  query {
    allResReviews {
      content
      user {
        ID
      }
      rate
      createdAt
      seq
      reply {
        content
      }
      images {
        image
      }
      resseq
    }
  }
`;

export const GET_RESTAURANT = gql`
  query {
    getRestaurant(seq: 1) {
      dayoff
      name
      category
      isopen
      delivery_tip
      introduction
      min_order
    }
  }
`;
