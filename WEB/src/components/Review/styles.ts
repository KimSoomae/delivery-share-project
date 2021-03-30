import styled from 'styled-components';

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

export const TableBody = styled.tbody`
  text-align: left;
`;

export const TableRow = styled.tr<{ no_pointer?: boolean }>`
  height: 3rem;
  cursor: pointer;
  transform: all 0.5s;

  &:hover {
    opacity: 0.75;
  }

  ${({ no_pointer }) =>
    no_pointer &&
    `
    & {
      cursor: auto !important;
    }

    &:hover {
      opacity: 1;
    }
  `};
`;

export const TableCell = styled.td.attrs<{ colSpan?: number }>(({ colSpan }) => {
  colSpan;
})`
  padding: 1.5rem 0;
  border-bottom: 1px solid #eaeaea;

  .score {
    font-weight: bold;
    font-size: 2rem;
  }

  img {
    width: 50;
    height: 50px;
  }

  svg {
    font-size: 1.5rem;
    color: #f77204;
  }
`;

export const InputText = styled.input.attrs(() => {
  type: 'text';
})`
  width: 100%;
  padding: 1rem;
  height: 4rem;
  border-radius: 10px;
  border: 1px solid #aeaeae;
`;

export const SubmitButton = styled.button`
  float: right;
  padding: 0.5rem 2rem;
  margin-top: 1rem;
  border-radius: 10px;
  outline: none;
  border: none;
  background-color: #f77204;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
`;
