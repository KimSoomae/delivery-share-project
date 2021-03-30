import React, { VFC } from 'react';
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
  onCloseModal: () => void;
  setShowModal: (flag: boolean) => void;
}

const ReviewModal: VFC<Props> = ({ show, onCloseModal, setShowModal }) => {
  if (!show) return null;

  return (
    <Modal>
      <div>
        <ModalHeader>nickname님의 리뷰</ModalHeader>
        <ModalBody>
          <ModalItem>
            <h1>별점</h1>
          </ModalItem>
          <ModalItem>
            <h1>리뷰내용</h1>
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

export default ReviewModal;
