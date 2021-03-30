import React, { useCallback, VFC } from 'react';
import { Table, TableBody, TableCell, TableHeader, TableRow } from './styles';

interface Props {
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: string) => void;
}

const Tables: VFC<Props> = ({ setShowModal, setModalInfo }) => {
  const openModal = useCallback((e: React.MouseEvent<HTMLTableRowElement>) => {
    setShowModal(true);
    setModalInfo(e.currentTarget.id);
  }, []);

  return (
    <Table>
      <TableHeader>
        <TableRow>
          <TableCell>주문번호</TableCell>
          <TableCell>주문날짜</TableCell>
          <TableCell>주문자</TableCell>
          <TableCell>장소</TableCell>
          <TableCell>가격</TableCell>
          <TableCell>상태</TableCell>
        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow id="#1" onClick={openModal}>
          <TableCell>#1</TableCell>
          <TableCell>2021.03.20 17:20:41</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>서울특별시 동대문구</TableCell>
          <TableCell>55,000</TableCell>
          <TableCell>
            <span className="pending">대기중</span>
          </TableCell>
        </TableRow>
        <TableRow id="#2" onClick={openModal}>
          <TableCell>#2</TableCell>
          <TableCell>2021.03.21 12:20:41</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>서울특별시 동대문구</TableCell>
          <TableCell>55,000</TableCell>
          <TableCell>
            <span className="delivered">완료</span>
          </TableCell>
        </TableRow>
        <TableRow id="#3" onClick={openModal}>
          <TableCell>#3</TableCell>
          <TableCell>2021.03.22 13:20:41</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>서울특별시 동대문구</TableCell>
          <TableCell>55,000</TableCell>
          <TableCell>
            <span className="canceled">취소</span>
          </TableCell>
        </TableRow>
        <TableRow id="#4" onClick={openModal}>
          <TableCell>#4</TableCell>
          <TableCell>2021.03.23 14:20:41</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>서울특별시 동대문구</TableCell>
          <TableCell>55,000</TableCell>
          <TableCell>
            <span className="pending">대기중</span>
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
};

export default React.memo(Tables);
