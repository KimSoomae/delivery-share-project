import styled from 'styled-components';

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

export const TableHeader = styled.thead`
  font-size: 1.25rem;
  font-weight: bold;
  text-align: left;
`;

export const TableBody = styled.tbody`
  text-align: left;
`;

export const TableRow = styled.tr`
  height: 3rem;
  cursor: pointer;

  &:hover {
    opacity: 0.75;
  }
`;

export const TableCell = styled.td`
  padding: 1.5rem 0;
  border-bottom: 1px solid #eaeaea;

  span {
    border-radius: 10px;
    padding: 5px 10px;
    font-weight: bold;

    &.pending {
      background-color: #fff6e0;
      color: #ffb800;
    }
    &.delivered {
      background-color: rgba(215, 215, 215, 0.45);
    }
    &.canceled {
      background-color: rgba(58, 250, 27, 0.45);
      color: #2bc155;
    }
  }
`;
