import React, { useCallback, VFC } from 'react';
import {
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
  TableWrapper,
} from '@components/TableContents/styles';
import { dummyOrder } from '@utils/dummyDB';
import { MemoTableContentOrder } from '@components/TableContents';

interface Props {
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: string) => void;
}

const Order: VFC<Props> = ({ setShowModal, setModalInfo }) => {
  const openModal = useCallback(data => {
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
        {dummyOrder.map(data => (
          <TableRow key={data.id} onClick={() => openModal(data)}>
            <MemoTableContentOrder data={data} />
          </TableRow>
        ))}
      </TableBody>
    </TableWrapper>
  );
};

export default Order;
