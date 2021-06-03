import { gql } from '@apollo/client';

export const POST_REPLY = gql`
  mutation writeReply($content: String!, $resReviewSeq: Int!) {
    writeReply(content: $content, resReviewSeq: $resReviewSeq)
  }
`;
