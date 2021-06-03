import { ModalHeader, ModalItem } from '@components/OrderModal/styles';
import React, { useCallback, VFC } from 'react';
import {
  ButtonWrapper,
  CloseButton,
  Modal,
  ModalContent,
  ModalDescription,
  SubmitButton,
} from './styles';
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
          <ModalContent>
            <ModalItem>
              <img src={data.src} alt={data.alt} width={300} />
            </ModalItem>

            <ModalDescription>
              <ModalItem>
                <h2>메뉴소개</h2>
                <p>{data.desc}</p>
              </ModalItem>
              <ModalItem>
                <h2>메뉴가격</h2>
                <p>{data.price}</p>
              </ModalItem>
            </ModalDescription>
          </ModalContent>
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
