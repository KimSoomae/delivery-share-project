import NavItem from '@components/NavItem';
import React, { VFC } from 'react';
import { Footer, MenuHeader } from './styles';
import { GiStorkDelivery } from 'react-icons/gi';

const NavList: VFC = () => {
  return (
    <>
      <MenuHeader>
        <GiStorkDelivery />
        <span>Delivery Share</span>
      </MenuHeader>
      <NavItem />
      <Footer>
        <h2>매장정보</h2>
        <h3>동대문 엽기떡볶이</h3>
        <span>외대점 (Tel) 02-231-1234</span>
      </Footer>
    </>
  );
};

export default NavList;
