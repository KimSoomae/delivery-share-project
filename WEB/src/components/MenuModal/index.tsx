import { ModalHeader, ModalItem } from '@components/OrderModal/styles';
import React, { useCallback, VFC } from 'react';
import { ButtonWrapper, CloseButton, Modal, SubmitButton } from './styles';
import { ModalBody } from './../OrderModal/styles';

interface Props {
  show: boolean;
  data: any;
  setShowModal: (flag: boolean) => void;
}

const MenuModal: VFC<Props> = ({ show, data, setShowModal }) => {
  const onCloseModal = useCallback(() => {
    setShowModal(false);
  }, []);

  if (!show) return null;

  return (
    <Modal>
      <div>
        <ModalHeader>
          {data.category} {'>'} {data.name}
        </ModalHeader>
        <ModalBody>
          <ModalItem>
            <h1>메뉴사진</h1>
            <img src={data.src} alt={data.alt} width={300} />
          </ModalItem>
          <ModalItem>
            <h1>메뉴소개</h1>
            <p>{data.desc}</p>
          </ModalItem>
          <ModalItem>
            <h1>메뉴가격</h1>
            <p>{data.price}</p>
          </ModalItem>
          <ButtonWrapper>
            <SubmitButton>수정</SubmitButton>
            <CloseButton onClick={onCloseModal}>취소</CloseButton>
          </ButtonWrapper>
        </ModalBody>
      </div>
    </Modal>
  );
};

export default MenuModal;
