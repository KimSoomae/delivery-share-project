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
      createdAt
      rate
      image
      seq
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
