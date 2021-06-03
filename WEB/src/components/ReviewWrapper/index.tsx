import React, { useEffect, useState, VFC } from 'react';
import { dummyReview } from '@utils/dummyDB';
import { TableBody, TableWrapper } from '@components/TableContents/styles';
import Review from '@components/Review';
import Pagination from '@components/Pagination';
import { PropsReview } from '@utils/type';

const PER_PAGE = 6;

type Props = {
  reviews: PropsReview[];
};

const ReviewWrapper: VFC<Props> = ({ reviews }) => {
  const reversed = reviews.slice().reverse();
  const rest = reversed.length % PER_PAGE;
  const pages = ((reversed.length / PER_PAGE) >> 0) + (rest ? 1 : 0);
  const [page, setPage] = useState(1);
  const [curReviews, setCurReviews] = useState(
    reversed.slice((page - 1) * PER_PAGE, page * PER_PAGE),
  );

  useEffect(() => {
    setCurReviews(reversed.slice((page - 1) * PER_PAGE, page * PER_PAGE));
  }, [page]);

  useEffect(() => {
    const target = document.querySelector(`.pagination-num-${page}`);
    target?.classList.add('selected');
    return () => {
      target?.classList.remove('selected');
    };
  }, [page]);

  const onClickPage = (page: number) => {
    setPage(page);
  };

  return (
    <TableWrapper>
      <TableBody>
        {curReviews.map((data: PropsReview) => (
          <Review key={data.seq} data={data} />
        ))}
      </TableBody>
      <Pagination pages={pages} onClickPage={onClickPage} />
    </TableWrapper>
  );
};

export default ReviewWrapper;
