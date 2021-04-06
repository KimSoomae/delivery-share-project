import styled from 'styled-components';

export const FormWrapper = styled.div`
  height: 0;
  width: 100%;
  overflow: hidden;
  transition: height 0.35s ease;
`;

export const ReviewForm = styled.form`
  /* display: flex;
  flex-direction: column; */
  overflow: auto;

  svg {
    transform: rotate(180deg);
    font-size: 3.5rem;
    margin-left: 10%;
    margin-top: 1.5rem;
  }
`;

export const Divider = styled.div`
  clear: both;
  padding: 0.5rem 0;
`;

export const Input = styled.textarea`
  width: 75%;
  height: 100px;
  border-radius: 10px;
  outline: none;
  border: 1px solid #888;
  padding: 15px;
  float: right;
`;

export const SubmitButton = styled.button`
  display: block;
  width: 100px;
  padding: 1rem 2rem;
  color: #fff;
  font-weight: bold;
  border-radius: 15px;
  background-color: #f77204;
  border: none;
  float: right;
`;

export const Comment = styled.div`
  width: 75%;
  height: 100px;
  border-radius: 10px;
  background-color: #ededed;
  padding: 15px;
  float: right;
`;
