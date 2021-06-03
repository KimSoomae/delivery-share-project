import { gql } from '@apollo/client';

export const GET_ORDERS = gql`
  query {
    allOrders {
      seq
      status
      sum
      call {
        callLocation {
          place
        }
        price
        user {
          ID
        }
        calltext
        created_at
      }
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
