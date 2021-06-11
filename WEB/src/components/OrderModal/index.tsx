import { PropsOrder } from '@utils/type';
import React, { useCallback, VFC } from 'react';
import { v4 as uuid } from 'uuid';
import { AiOutlineCloseCircle } from 'react-icons/ai';
import {
  ButtonWrapper,
  CancelButton,
  Modal,
  ModalBody,
  ModalHeader,
  ModalItem,
  SubmitButton,
} from './styles';
import { useMutation } from '@apollo/client';
import { UPDATE_ORDER } from '@Apollo/mutations';

interface Props {
  show: boolean;
  data: PropsOrder | undefined;
  setShowModal: (flag: boolean) => void;
}

const OrderModal: VFC<Props> = ({ show, data, setShowModal }) => {
  const [updateOrder] = useMutation(UPDATE_ORDER);

  const onCloseModal = useCallback(() => {
    setShowModal(false);
  }, []);

  const handleCancel = () => {
    updateOrder({
      variables: {
        deliveryTime: 30,
        isApproved: false,
        seq: data?.seq,
      },
    });
    window.location.reload();
  };

  const handleSubmit = () => {
    updateOrder({
      variables: {
        deliveryTime: 30,
        isApproved: true,
        seq: data?.seq,
      },
    });
    window.location.reload();
  };

  if (!show) return null;

  const menus = data?.call?.cart;
  const selectedMenu = menus?.map(menu => menu.selected_menu);
  const requests = menus?.map(menu => menu.request);

  const [menuA, menuB] = selectedMenu || [null, null];
  const myMenu1 = menuA?.map(menu => menu.menu);
  const myMenu2 = menuB?.map(menu => menu.menu);

  return (
    <Modal>
      <div>
        <ModalHeader>
          <h1>주문번호 #{data?.seq}</h1>
          <AiOutlineCloseCircle onClick={onCloseModal} />
        </ModalHeader>
        <ModalBody>
          <ModalItem>
            <h1>주문메뉴</h1>
            <div>
              {myMenu1?.map(menu => (
                <p key={uuid()}>{menu.name}</p>
              ))}
              {myMenu2?.map(menu => (
                <p key={uuid()}>{menu.name}</p>
              ))}
            </div>
          </ModalItem>
          <ModalItem>
            <h1>요청사항</h1>
            <div>
              {requests?.map(request => (
                <p key={uuid()}>{request || '없음'}</p>
              ))}
            </div>
          </ModalItem>
          <ModalItem>
            <h1>시간</h1>
            <div>
              <p>{data?.call?.created_at.substr(0, 19).split('T').join(' ')}</p>
            </div>
          </ModalItem>
          <ModalItem>
            <h1>장소</h1>
            <div>
              <p>{data?.call?.callLocation?.place}</p>
            </div>
          </ModalItem>
          <ButtonWrapper>
            <SubmitButton onClick={handleSubmit}>접수</SubmitButton>
            <CancelButton onClick={handleCancel}>취소</CancelButton>
          </ButtonWrapper>
        </ModalBody>
      </div>
    </Modal>
  );
};

export default OrderModal;
