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
              {data.menus.length &&
                data.menus.map((menu: { id: string; name: string; count: number }) => (
                  <p key={menu.id}>
                    {menu.name} x {menu.count}
                  </p>
                ))}
            </div>
          </ModalItem>
          <ModalItem>
            <h1>요청사항</h1>
            <div>
              {data.requests.length ? (
                data.requests.map(
                  (req: { id: string; content: string; nickname: string }) => (
                    <p key={req.id}>{req.content}</p>
                  ),
                )
              ) : (
                <p>요청사항이 없습니다.</p>
              )}
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
