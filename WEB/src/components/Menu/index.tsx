import { SubTitle, Grid, GridItem } from '@components/Menu/styles';
import { LazyLoadImage } from 'react-lazy-load-image-component';
import React, { useCallback, VFC } from 'react';

interface Props {
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: string) => void;
}

const Menu: VFC<Props> = ({ setShowModal, setModalInfo }) => {
  const openModal = useCallback((e: React.MouseEvent<HTMLDivElement>) => {
    setShowModal(true);
    setModalInfo(e.currentTarget.id);
  }, []);

  return (
    <>
      <SubTitle>치킨</SubTitle>
      <Grid>
        <GridItem id="치킨 > 뿌링클" onClick={openModal}>
          <LazyLoadImage src="/images/chicken1.jpg" alt="chicken1-img" />
          <p className="menu-name">뿌링클</p>
        </GridItem>
        <GridItem id="치킨 > 포테킹" onClick={openModal}>
          <LazyLoadImage src="/images/chicken2.png" alt="chicken2-img" />
          <p className="menu-name">포테킹</p>
        </GridItem>
      </Grid>

      <SubTitle>사이드</SubTitle>
      <Grid>
        <GridItem id="사이드 > 치즈볼" onClick={openModal}>
          <LazyLoadImage src="/images/cheeseball.png" alt="cheeseball-img" />
          <p className="menu-name">치즈볼</p>
        </GridItem>
      </Grid>
    </>
  );
};

export default Menu;
