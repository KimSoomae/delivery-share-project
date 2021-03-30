import styled from 'styled-components';

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

export const GridItem = styled.div`
  display: flex;
  flex-direction: column;
  cursor: pointer;

  img {
    flex: 1;
    width: 100%;
    object-fit: contain;
  }

  .menu-name {
    text-align: center;
    font-size: 1.2rem;
    font-weight: bold;
  }

  &:hover {
    opacity: 0.75;
  }
`;
