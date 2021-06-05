import React, { VFC } from 'react';
import { TableCell } from './styles';
import MakeStars from '@utils/MakeStars';
import { PropsOrder, PropsReview } from '@utils/type';
import { BsCheckCircle } from 'react-icons/bs';

type ReviewProps = {
  data: PropsReview;
  refresh: boolean;
};

const TableContentReview: VFC<ReviewProps> = ({ data, refresh }) => {
  const { user, rate, reply } = data;
  const classProperty = reply || refresh ? 'checked' : '';
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
  const { seq, status, call } = data;
  const text = status === 'pending' ? '대기중' : status === 'completed' ? '완료' : '취소';
  const cellCount = 6;

  const menus = call?.cart;
  const selectedMenu = menus?.map(menu => menu.selected_menu);

  const [menuA, menuB] = selectedMenu || [null, null];
  const myMenu1 = menuA?.map(menu => menu.menu);
  const myMenu2 = menuB?.map(menu => menu.menu);

  const price =
    myMenu1?.reduce((a, b) => a + b.price, 0) + myMenu2?.reduce((a, b) => a + b.price, 0);

  return (
    <>
      <TableCell flexStart={true} cellCount={cellCount}>
        #{seq}
      </TableCell>
      <TableCell flexStart={true} cellCount={cellCount} className="tw-wd">
        {call?.callLocation?.place || '외대 머나먼 곳 어딘가..'}
      </TableCell>
      <TableCell flexStart={true} cellCount={cellCount} className="tw-wd">
        {call?.created_at.substr(0, 10) || '2021-05-21'}
      </TableCell>
      <TableCell cellCount={cellCount} className="tn-wd">
        {call?.user?.ID || '익명'}
      </TableCell>
      <TableCell cellCount={cellCount} className="tn-wd">
        {price.toLocaleString('ko-KR')}
      </TableCell>
      <TableCell cellCount={cellCount}>
        <span className={status}>{text}</span>
      </TableCell>
    </>
  );
};
export const MemoTableContentOrder = React.memo(TableContentOrder);
