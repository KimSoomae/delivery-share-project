import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 80vh;
  width: 80%;
  max-width: 1200px;
  margin: auto;
`;

export const SubTitle = styled.div`
  padding: 1rem;
  width: 150px;
  text-align: center;
  font-size: 1.2rem;
  font-weight: bold;
  color: #fff;
  background-color: #f77204;
  border-radius: 30px;
`;

export const Grid = styled.div`
  display: grid;
  grid-gap: 10px;
  grid-template-columns: repeat(3, 1fr);
  margin: 3rem 0 5rem 0;
`;
