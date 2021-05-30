import styled from 'styled-components';

export const GridItem = styled.div`
  display: flex;
  flex-direction: column;
  cursor: pointer;

  img {
    flex: 1;
    width: 100%;
    object-fit: cover;
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
