import React, { VFC } from 'react';
import { dummyReview } from '@utils/dummyDB';
import { TableBody, TableWrapper } from '@components/TableContents/styles';
import Review from '@components/Review';

const ReviewWrapper: VFC = () => {
  return (
    <TableWrapper>
      <TableBody>
        {dummyReview.map(data => (
          <Review key={data.id} data={data} />
        ))}
      </TableBody>
    </TableWrapper>
  );
};

export default ReviewWrapper;
