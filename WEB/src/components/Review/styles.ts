import styled from 'styled-components';

export const FormWrapper = styled.div`
  height: 0;
  width: 100%;
  overflow: hidden;
  transition: height 0.35s ease;
`;

export const UserReview = styled.div`
  border-radius: 10px;
  padding: 15px;
  background-color: #eee;
  margin: 1rem 0;
  min-height: 100px;
  width: 80%;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 10px solid #eee;
    top: -10px;
    left: 20px;
  }
`;

export const ReviewForm = styled.form`
  /* display: flex;
  flex-direction: column; */
  overflow: auto;
  padding-bottom: 20px;

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
  background-color: #e5e5e5;
  padding: 15px;
  float: right;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-top: 10px solid #e5e5e5;
    bottom: -10px;
    right: 20px;
  }
`;
