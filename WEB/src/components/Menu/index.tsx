import { GridItem } from '@components/Menu/styles';
import { LazyLoadImage } from 'react-lazy-load-image-component';
import React, { useCallback, VFC } from 'react';
import { PropsMenu } from '@utils/type';

interface Props {
  setShowModal: (flag: boolean) => void;
  setModalInfo: (content: PropsMenu) => void;
  data: PropsMenu;
}

const Menu: VFC<Props> = ({ setShowModal, setModalInfo, data }) => {
  const { name, src, alt } = data;
  const openModal = useCallback(data => {
    setShowModal(true);
    setModalInfo(data);
  }, []);

  return (
    <GridItem onClick={() => openModal(data)}>
      <LazyLoadImage src={src} alt={alt} />
      <p className="menu-name">{name}</p>
    </GridItem>
  );
};

export default React.memo(Menu);
