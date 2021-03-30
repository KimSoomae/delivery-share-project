import NavItem from '@components/NavItem';
import React, { VFC } from 'react';
import { MenuHeader } from './styles';
import { GiStorkDelivery } from 'react-icons/gi';

const NavList: VFC = () => {
  return (
    <>
      <MenuHeader>
        <GiStorkDelivery />
        <span>Delivery Share</span>
      </MenuHeader>
      <NavItem />
    </>
  );
};

export default NavList;
