import styled from 'styled-components';

// ** -------- 공용 styled ---------- **
export const TableWrapper = styled.div`
  width: 100%;
`;

export const TableHeader = styled.div`
  display: flex;
  flex-direction: column;
  font-size: 1.25rem;
  font-weight: bold;
`;

export const TableBody = styled.div`
  display: flex;
  flex-direction: column;
`;

export const TableRow = styled.div`
  cursor: pointer;
  display: flex;
  justify-content: space-around;
  padding: 1.5rem 0;
  border-top: 1px solid #ededed;
  gap: 20px;
  box-sizing: border-box;

  &:hover {
    opacity: 0.75;
  }
`;
// ** ---------------------------------- **

export const TableCell = styled.div<{ cellCount?: number; flexStart?: boolean }>`
  width: ${({ cellCount }) => cellCount && 100 / cellCount}%;
  display: flex;
  align-items: center;
  justify-content: ${({ flexStart }) =>
    flexStart && flexStart ? 'flex-start' : 'center'};

  &.icon {
    width: 50px;
  }
  &.nickname {
    width: 10%;

    p {
      width: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }

  &.content {
    justify-content: flex-start;
    width: 30%;
    min-width: 90px;

    p {
      width: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }

  &.tn-wd {
    width: 10%;
  }
  &.tw-wd {
    width: 23.333333333333336%;
  }

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

  svg.check-icon {
    font-size: 2.5rem;
    color: #9d9d9d;
  }
  svg.check-icon.checked {
    color: #31ad3b;
  }

  span {
    border-radius: 10px;
    padding: 5px 10px;
    font-weight: bold;

    &.pending {
      background-color: #fff6e0;
      color: #ffb800;
    }
    &.canceled {
      background-color: rgba(215, 215, 215, 0.45);
    }
    &.delivered {
      background-color: rgba(58, 250, 27, 0.45);
      color: #2bc155;
    }
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
