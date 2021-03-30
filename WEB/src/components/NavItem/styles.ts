import styled from 'styled-components';

export const MenuItem = styled.li`
  position: relative;
  font-size: 1.2rem;
  color: #fff;
  margin: 2rem 0;
  text-align: center;
  border-top-left-radius: 30px;
  border-bottom-left-radius: 30px;
  padding: 1rem;
  cursor: pointer;

  &:hover {
    font-weight: bold;
  }

  svg {
    position: absolute;
    left: 2rem;
    font-size: 1.85rem;
  }
`;

export const MenuWrapper = styled.ul`
  list-style: none;

  & a {
    text-decoration: none;

    &.selected ${MenuItem} {
      background-color: #fff;
      color: #f77204;
      font-weight: bold;
    }
  }
`;
