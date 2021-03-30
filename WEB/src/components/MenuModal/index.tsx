import { ModalHeader, ModalItem } from '@components/OrderModal/styles';
import React, { VFC } from 'react';
import { ButtonWrapper, CloseButton, Modal, SubmitButton } from './styles';
import { ModalBody } from './../OrderModal/styles';

interface Props {
  show: boolean;
  info: string;
  onCloseModal: () => void;
  setShowModal: (flag: boolean) => void;
}

const MenuModal: VFC<Props> = ({ show, info, onCloseModal, setShowModal }) => {
  if (!show) return null;

  return (
    <Modal>
      <div>
        <ModalHeader>{info}</ModalHeader>
        <ModalBody>
          <ModalItem>
            <h1>메뉴사진</h1>
          </ModalItem>
          <ModalItem>
            <h1>메뉴소개</h1>
          </ModalItem>
          <ModalItem>
            <h1>메뉴가격</h1>
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
