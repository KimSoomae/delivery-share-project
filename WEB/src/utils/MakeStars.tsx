import React, { VFC } from 'react';
import { MdStar, MdStarBorder, MdStarHalf } from 'react-icons/md';
import { v4 as uuid } from 'uuid';

interface Props {
  stars: number;
}

const MakeStars: VFC<Props> = ({ stars }) => {
  const mainScore = stars >> 0;
  const restScore = stars % 1;
  const noScore = 5 - Math.ceil(stars);
  const starIcons = [];

  for (let i = 0; i < mainScore; i++) {
    starIcons.push(<MdStar key={uuid()} />);
  }
  for (let i = 0; i < restScore; i++) {
    starIcons.push(<MdStarHalf key={uuid()} />);
  }
  for (let i = 0; i < noScore; i++) {
    starIcons.push(<MdStarBorder key={uuid()} />);
  }

  return <>{starIcons}</>;
};

export default MakeStars;
