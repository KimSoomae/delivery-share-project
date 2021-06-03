import { PropsOrder } from '@utils/type';
import React, { useCallback, VFC } from 'react';
import {
  ButtonWrapper,
  CloseButton,
  Modal,
  ModalBody,
  ModalHeader,
  ModalItem,
  SubmitButton,
} from './styles';

interface Props {
  show: boolean;
  data: PropsOrder | undefined;
  setShowModal: (flag: boolean) => void;
}

const OrderModal: VFC<Props> = ({ show, data, setShowModal }) => {
  const onCloseModal = useCallback(() => {
    setShowModal(false);
  }, []);

  if (!show) return null;

  return (
    <Modal>
      <div>
        <ModalHeader>주문번호 #{data?.seq}</ModalHeader>
        <ModalBody>
          <ModalItem>
            <h1>주문메뉴</h1>
            <div>{data?.call?.cart && <p>1</p>}</div>
          </ModalItem>
          <ModalItem>
            <h1>요청사항</h1>
            <div>
              {data?.call?.request_R ? (
                <p>{data?.call.request_R}</p>
              ) : (
                <p>요청사항이 없습니다.</p>
              )}
            </div>
          </ModalItem>
          <ModalItem>
            <h1>시간</h1>
            <div>
              <p>{data?.call?.created_at}</p>
            </div>
          </ModalItem>
          <ModalItem>
            <h1>장소</h1>
            <div>
              <p>{data?.call?.callLocation?.place}</p>
            </div>
          </ModalItem>
          <ButtonWrapper>
            <SubmitButton>접수</SubmitButton>
            <CloseButton onClick={onCloseModal}>취소</CloseButton>
          </ButtonWrapper>
        </ModalBody>
      </div>
    </Modal>
  );
};

export default OrderModal;
