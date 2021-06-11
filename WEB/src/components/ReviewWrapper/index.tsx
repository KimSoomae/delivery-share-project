import React, { useEffect, useState, VFC } from 'react';
import { TableBody, TableWrapper } from '@components/TableContents/styles';
import Review from '@components/Review';
import Pagination from '@components/Pagination';
import { useQuery } from '@apollo/client';
import { GET_RES_REVIEWS } from '@Apollo/quries';
import { PropsReview } from '@utils/type';
import Spinner from '@utils/Spinner';

const PER_PAGE = 6;

const ReviewWrapper: VFC = () => {
  const { loading, data, refetch } = useQuery(GET_RES_REVIEWS);
  const reviews = data?.allResReviews || [];

  const reversed = reviews.slice().reverse();
  const rest = reversed.length % PER_PAGE;
  const pages = ((reversed.length / PER_PAGE) >> 0) + (rest ? 1 : 0);
  const [page, setPage] = useState(1);
  const [curReviews, setCurReviews] = useState(
    reversed.slice((page - 1) * PER_PAGE, page * PER_PAGE),
  );

  useEffect(() => {
    refetch();
  }, []);

  useEffect(() => {
    setCurReviews(reversed.slice((page - 1) * PER_PAGE, page * PER_PAGE));
  }, [loading, page]);

  useEffect(() => {
    const target = document.querySelector(`.pagination-num-${page}`);
    target?.classList.add('selected');
    return () => {
      target?.classList.remove('selected');
    };
  }, [page]);

  const onClickPage = (page: number) => {
    refetch();
    setPage(page);
  };

  if (loading) {
    return <Spinner />;
  }

  return (
    <TableWrapper>
      <TableBody>
        {curReviews.map((data: PropsReview) => (
          <Review key={data.seq} info={data} />
        ))}
      </TableBody>
      <Pagination pages={pages} onClickPage={onClickPage} />
    </TableWrapper>
  );
};

export default ReviewWrapper;
