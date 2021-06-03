import { gql } from '@apollo/client';

export const GET_ORDERS = gql`
  query {
    allOrders {
      seq
      status
      sum
      call {
        request_R
        request_call
        callLocation {
          place
        }
        price
        user {
          ID
        }
        calltext
        created_at
        cart {
          selected_menu {
            menu {
              name
              price
            }
          }
        }
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
