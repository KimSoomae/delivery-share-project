import { gql } from '@apollo/client';

export const POST_REPLY = gql`
  mutation writeReply($content: String!, $resReviewSeq: Int!) {
    writeReply(content: $content, resReviewSeq: $resReviewSeq)
  }
`;

export const UPDATE_ORDER = gql`
  mutation checkOrder($deliveryTime: Int!, $isApproved: Boolean!, $seq: Int!) {
    checkOrder(deliveryTime: $deliveryTime, isApproved: $isApproved, seq: $seq) {
      status
    }
  }
`;
