import React, { VFC } from 'react';
import { TableCell } from './styles';
import MakeStars from '@utils/MakeStars';
import { PropsOrder, PropsReview } from '@utils/type';

type ReviewProps = {
  data: PropsReview;
};
const TableContentReview: VFC<ReviewProps> = ({ data }) => {
  const { src, alt, content, nickname, stars } = data;
  const cellCount = 5;
  return (
    <>
      <TableCell cellCount={cellCount}>
        <img src={src} alt={alt} />
      </TableCell>
      <TableCell cellCount={cellCount} className="content">
        <p>{content}</p>
      </TableCell>
      <TableCell cellCount={cellCount} className="nickname">
        <p>{nickname}</p>
      </TableCell>
      <TableCell cellCount={cellCount}>
        <MakeStars stars={stars} />
      </TableCell>
      <TableCell cellCount={cellCount}>
        <span className="score">{stars.toFixed(1)}</span>
      </TableCell>
    </>
  );
};
export const MemoTableContentReview = React.memo(TableContentReview);

type OrderProps = {
  data: PropsOrder;
};
const TableContentOrder: VFC<OrderProps> = ({ data }) => {
  const { id, location, date, nickname, price, state } = data;
  const text = state === 'pending' ? '대기중' : state === 'delivered' ? '완료' : '취소';
  const cellCount = 6;
  return (
    <>
      <TableCell flexStart={true} cellCount={cellCount}>
        #{id.substr(0, 8)}
      </TableCell>
      <TableCell flexStart={true} cellCount={cellCount} className="tw-wd">
        {location}
      </TableCell>
      <TableCell flexStart={true} cellCount={cellCount} className="tw-wd">
        {date}
      </TableCell>
      <TableCell cellCount={cellCount} className="tn-wd">
        {nickname}
      </TableCell>
      <TableCell cellCount={cellCount} className="tn-wd">
        {price}
      </TableCell>
      <TableCell cellCount={cellCount}>
        <span className={state}>{text}</span>
      </TableCell>
    </>
  );
};
export const MemoTableContentOrder = React.memo(TableContentOrder);
