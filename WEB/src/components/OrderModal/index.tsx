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
  data: any;
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
        <ModalHeader>주문번호 #{data.id.substr(0, 8)}</ModalHeader>
        <ModalBody>
          <ModalItem>
            <h1>주문메뉴</h1>
            <div>
              <p>뿌링클 x 1</p>
              <p>치즈볼(5개입) x 1</p>
            </div>
          </ModalItem>
          <ModalItem>
            <h1>요청사항</h1>
            <div>
              <p>조심히 오세요~</p>
              <p>2인분으로 나눠주세요~</p>
            </div>
          </ModalItem>
          <ModalItem>
            <h1>시간</h1>
            <div>
              <p>{data.date}</p>
            </div>
          </ModalItem>
          <ModalItem>
            <h1>장소</h1>
            <div>
              <p>{data.location}</p>
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
