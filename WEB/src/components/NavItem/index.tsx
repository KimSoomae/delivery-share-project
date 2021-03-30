import React, { VFC } from 'react';
import { NavLink } from 'react-router-dom';
import { MenuItem, MenuWrapper } from './styles';
import { FaRegListAlt } from 'react-icons/fa';
import { MdRestaurant, MdAccessAlarms } from 'react-icons/md';
import { IoFastFoodOutline } from 'react-icons/io5';

const NavItem: VFC = () => {
  return (
    <MenuWrapper>
      <NavLink to="/dashboard/order" activeClassName="selected">
        <MenuItem>
          <MdAccessAlarms />
          <span>주문접수</span>
        </MenuItem>
      </NavLink>

      <NavLink to="/dashboard/menu" activeClassName="selected">
        <MenuItem>
          <IoFastFoodOutline />
          <span>메뉴관리</span>
        </MenuItem>
      </NavLink>
      <NavLink to="/dashboard/review" activeClassName="selected">
        <MenuItem>
          <FaRegListAlt />
          <span>리뷰관리</span>
        </MenuItem>
      </NavLink>
      <NavLink to="/dashboard/info" activeClassName="selected">
        <MenuItem>
          <MdRestaurant />
          <span>식당정보</span>
        </MenuItem>
      </NavLink>
    </MenuWrapper>
  );
};

export default React.memo(NavItem);
