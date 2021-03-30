import Menu from '@components/Menu';
import React, { VFC } from 'react';
import { Container } from './styles';

interface Props {
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: string) => void;
}

const Menus: VFC<Props> = ({ setShowModal, setModalInfo }) => {
  return (
    <Container>
      <Menu setShowModal={setShowModal} setModalInfo={setModalInfo} />
    </Container>
  );
};

export default Menus;
