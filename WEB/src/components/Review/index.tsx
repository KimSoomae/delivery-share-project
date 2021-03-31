import React, { useCallback, useRef, useState, VFC } from 'react';
import { MemoTableContentReview } from '@components/TableContents';
import { dummyReview } from '@utils/dummyDB';
import { TableBody, TableWrapper, TableRow } from '@components/TableContents/styles';

const Review: VFC = () => {
  const [reviewCollapse, setReviewCollapse] = useState(false);
  // const parentRef = useRef<HTMLTableRowElement>(null);
  // const childRef = useRef<HTMLTableDataCellElement>(null);

  const toggleReviewCollapse = useCallback(() => {
    setReviewCollapse(prev => !prev);
  }, []);

  return (
    <TableWrapper>
      <TableBody>
        {dummyReview.map(data => (
          <TableRow key={data.id} onClick={toggleReviewCollapse}>
            <MemoTableContentReview data={data} />
          </TableRow>
        ))}
      </TableBody>
    </TableWrapper>
  );
};

export default Review;
