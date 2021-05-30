import React, { useCallback, useEffect, useState, VFC } from 'react';
import {
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
  TableWrapper,
} from '@components/TableContents/styles';
import { dummyOrder } from '@utils/dummyDB';
import { MemoTableContentOrder } from '@components/TableContents';
import Pagination from '@components/Pagination';

interface Props {
  orders: [any];
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: string) => void;
}

const PER_PAGE = 7;

const Order: VFC<Props> = ({ orders, setShowModal, setModalInfo }) => {
  const rest = orders.length % PER_PAGE;
  const pages = ((orders.length / PER_PAGE) >> 0) + (rest ? 1 : 0);
  const [page, setPage] = useState(1);
  const [curOrders, setCurOrders] = useState(
    orders.slice((page - 1) * PER_PAGE, page * PER_PAGE),
  );

  console.log(orders, curOrders);

  useEffect(() => {
    setCurOrders(orders.slice((page - 1) * PER_PAGE, page * PER_PAGE));
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

  const onOpenModal = useCallback(data => {
    setShowModal(true);
    setModalInfo(data);
  }, []);

  return (
    <TableWrapper>
      <TableHeader>
        <TableRow>
          <TableCell flexStart={true} cellCount={6}>
            주문번호
          </TableCell>
          <TableCell flexStart={true} cellCount={6} className="tw-wd">
            장소
          </TableCell>
          <TableCell flexStart={true} cellCount={6} className="tw-wd">
            시간
          </TableCell>
          <TableCell cellCount={6} className="tn-wd">
            주문자
          </TableCell>
          <TableCell cellCount={6} className="tn-wd">
            가격
          </TableCell>
          <TableCell cellCount={6}>상태</TableCell>
        </TableRow>
      </TableHeader>

      <TableBody>
        {curOrders.map(data => (
          <TableRow key={data.id} onClick={() => onOpenModal(data)}>
            <MemoTableContentOrder data={data} />
          </TableRow>
        ))}
      </TableBody>
      <Pagination pages={pages} onClickPage={onClickPage} />
    </TableWrapper>
  );
};

export default Order;
