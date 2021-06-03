import React, { VFC } from 'react';
import { TableCell } from './styles';
import MakeStars from '@utils/MakeStars';
import { PropsOrder, PropsReview } from '@utils/type';
import { BsCheckCircle } from 'react-icons/bs';

type ReviewProps = {
  data: PropsReview;
};

const TableContentReview: VFC<ReviewProps> = ({ data }) => {
  const { images, user, content, createdAt, seq, resseq, rate, reply } = data;
  const classProperty = reply ? 'checked' : '';
  const cellCount = 5;
  return (
    <>
      <TableCell cellCount={cellCount} className="icon">
        <BsCheckCircle className={'check-icon ' + classProperty} />
      </TableCell>
      <TableCell cellCount={cellCount}>
        <img src={'../images/profile-default.png'} alt="유저 리뷰 이미지" />
      </TableCell>
      <TableCell cellCount={cellCount} className="nickname">
        <p>{user?.ID || '익명'}</p>
      </TableCell>
      <TableCell cellCount={cellCount}>
        <MakeStars stars={rate} />
      </TableCell>
      <TableCell cellCount={cellCount}>
        <span className="score">{rate.toFixed(1)}</span>
      </TableCell>
    </>
  );
};
export const MemoTableContentReview = React.memo(TableContentReview);

type OrderProps = {
  data: PropsOrder;
};
const TableContentOrder: VFC<OrderProps> = ({ data }) => {
  const { seq, status } = data;
  const text = status === 'pending' ? '대기중' : status === 'completed' ? '완료' : '취소';
  const cellCount = 6;
  return (
    <>
      <TableCell flexStart={true} cellCount={cellCount}>
        #{seq}
      </TableCell>
      <TableCell flexStart={true} cellCount={cellCount} className="tw-wd">
        위치
      </TableCell>
      <TableCell flexStart={true} cellCount={cellCount} className="tw-wd">
        날짜
      </TableCell>
      <TableCell cellCount={cellCount} className="tn-wd">
        유저
      </TableCell>
      <TableCell cellCount={cellCount} className="tn-wd">
        가격
      </TableCell>
      <TableCell cellCount={cellCount}>
        <span className={status}>{text}</span>
      </TableCell>
    </>
  );
};
export const MemoTableContentOrder = React.memo(TableContentOrder);
