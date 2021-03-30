import styled from 'styled-components';

export const MapImage = styled.div`
  width: 100%;
  overflow: hidden;

  img {
    width: 100%;
    max-height: 500px;
    object-fit: cover;
  }
`;

export const ListWrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 3rem;
  margin-top: 3rem;
`;

export const SubTitle = styled.span`
  padding: 1rem;
  min-width: 150px;
  text-align: center;
  font-size: 1.2rem;
  font-weight: bold;
  color: #fff;
  background-color: #f77204;
  border-radius: 30px;
`;

export const Content = styled.span`
  font-size: 1.15rem;
`;
