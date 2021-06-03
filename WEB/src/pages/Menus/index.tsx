import Menu from '@components/Menu';
import MenuModal from '@components/MenuModal';
import React, { useState, VFC } from 'react';
import { Container, Grid, SubTitle } from './styles';
import { dummyMenu } from './../../utils/dummyDB';
import { PropsMenu } from '@utils/type';

const Menus: VFC = () => {
  const [showMenuModal, setShowMenuModal] = useState(false);
  const [menuInfo, setMenuInfo] = useState<PropsMenu>();

  return (
    <Container>
      <SubTitle>떡볶이</SubTitle>
      <Grid>
        {dummyMenu.slice(0, 3).map(data => (
          <Menu
            key={data.id}
            data={data}
            setShowModal={setShowMenuModal}
            setModalInfo={setMenuInfo}
          />
        ))}
      </Grid>

      <SubTitle>사이드</SubTitle>
      <Grid>
        {dummyMenu.slice(3).map(data => (
          <Menu
            key={data.id}
            data={data}
            setShowModal={setShowMenuModal}
            setModalInfo={setMenuInfo}
          />
        ))}
      </Grid>
      <MenuModal show={showMenuModal} data={menuInfo} setShowModal={setShowMenuModal} />
    </Container>
  );
};

export default Menus;
