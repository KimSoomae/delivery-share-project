import React, { VFC } from 'react';
import { PaginationWrapp, Page } from './styles';

type Props = {
  pages: number;
  onClickPage: (page: number) => void;
};

const Pagination: VFC<Props> = ({ pages, onClickPage }) => {
  const pageBtns: number[] = new Array(pages).fill(1);
  for (let i = 1; i < pages; i++) {
    pageBtns[i] = pageBtns[i - 1] + 1;
  }

  return (
    <PaginationWrapp>
      {pageBtns.length &&
        pageBtns.map(page => (
          <Page
            className={`pagination-num-${page}`}
            onClick={() => onClickPage(page)}
            key={page}>
            {page}
          </Page>
        ))}
    </PaginationWrapp>
  );
};

export default Pagination;
