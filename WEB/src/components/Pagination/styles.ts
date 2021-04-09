import styled from 'styled-components';

export const PaginationWrapp = styled.div`
  display: flex;
  width: 100%;
  justify-content: flex-end;
  gap: 5px;
  margin-top: 1rem;
  margin-bottom: 3rem;
`;

export const Page = styled.div`
  padding: 0.6rem 1rem;
  background-color: #ededed;
  font-weight: bold;
  border-radius: 5px;
  cursor: pointer;

  &:hover {
    opacity: 0.75;
  }

  &.selected {
    background-color: #000;
    color: #fff;
  }
`;
