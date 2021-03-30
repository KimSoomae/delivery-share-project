import styled from 'styled-components';

export const Modal = styled.div`
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  z-index: 999;
  background-color: rgba(0, 0, 0, 0.75);

  & > div {
    display: flex;
    flex-direction: column;
    background-color: #fff;
    border-radius: 1rem;
    overflow: hidden;
    width: 80%;
    height: 80%;
    max-width: 1200px;
  }
`;

export const ModalHeader = styled.div`
  font-size: 3rem;
  font-weight: bold;
  background-color: #f77204;
  color: #fff;
  padding: 2rem;
`;

export const ModalBody = styled.div`
  flex: 1;
  padding: 2rem;
  position: relative;
`;

export const ModalItem = styled.div`
  & > div {
    display: flex;
    justify-content: space-between;

    & > p {
      width: 45%;
      padding: 1rem;
      border-radius: 1rem;
      background-color: #fff6e0;
    }
  }
`;

export const ButtonWrapper = styled.div`
  position: absolute;
  left: 0;
  bottom: 2rem;
  padding: 0 2rem;
  display: flex;
  width: 100%;
  justify-content: space-between;

  & > button {
    width: 35%;
    height: 4rem;
    border-radius: 15px;
    border: none;
    font-size: 1.5rem;
    font-weight: bold;
    cursor: pointer;

    &:hover {
      opacity: 0.85;
    }
  }
`;

export const CloseButton = styled.button`
  background-color: #dedede;
`;

export const SubmitButton = styled.button`
  background-color: #f77204;
  color: #fff;
`;
