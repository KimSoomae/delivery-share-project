import React, { useCallback, useRef, useState, VFC } from 'react';
import { InputText, SubmitButton, Table, TableBody, TableCell, TableRow } from './styles';
import MakeStars from '@utils/MakeStars';

interface Props {
  setShowModal: (flag: boolean) => void;
}

const Review: VFC<Props> = ({ setShowModal }) => {
  const [reviewCollapse, setReviewCollapse] = useState(false);
  const parentRef = useRef<HTMLTableRowElement>(null);
  const childRef = useRef<HTMLTableDataCellElement>(null);

  const openModal = useCallback(() => {
    setShowModal(true);
  }, []);

  const toggleReviewCollapse = useCallback(() => {
    setReviewCollapse(prev => !prev);
  }, []);

  return (
    <Table>
      <TableBody>
        <TableRow onClick={toggleReviewCollapse}>
          <TableCell>
            <img src="../images/profile-default.png" alt="user-default-profile-image" />
          </TableCell>
          <TableCell>맛있어요~</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>
            <MakeStars stars={4.0} />
          </TableCell>
          <TableCell>
            <span className="score">4.0</span>
          </TableCell>
        </TableRow>
        {reviewCollapse && (
          <TableRow no_pointer={true} ref={parentRef}>
            <TableCell ref={childRef} colSpan={5}>
              <InputText />
              <SubmitButton>등록</SubmitButton>
            </TableCell>
          </TableRow>
        )}

        <TableRow onClick={openModal}>
          <TableCell>
            <img src="../images/profile-default.png" alt="user-default-profile-image" />
          </TableCell>
          <TableCell>깔끔해요~</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>
            <MakeStars stars={4.0} />
          </TableCell>
          <TableCell>
            <span className="score">4.0</span>
          </TableCell>
        </TableRow>

        <TableRow onClick={openModal}>
          <TableCell>
            <img src="../images/profile-default.png" alt="user-default-profile-image" />
          </TableCell>
          <TableCell>별로에요~</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>
            <MakeStars stars={2.0} />
          </TableCell>
          <TableCell>
            <span className="score">2.0</span>
          </TableCell>
        </TableRow>

        <TableRow onClick={openModal}>
          <TableCell>
            <img src="../images/profile-default.png" alt="user-default-profile-image" />
          </TableCell>
          <TableCell>최고에요!</TableCell>
          <TableCell>nickname</TableCell>
          <TableCell>
            <MakeStars stars={4.5} />
          </TableCell>
          <TableCell>
            <span className="score">4.5</span>
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
};

export default Review;
