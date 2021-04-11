import React, { useCallback, useRef, VFC } from 'react';
import {
  Input,
  FormWrapper,
  ReviewForm,
  SubmitButton,
  Comment,
  Divider,
  UserReview,
} from '../Review/styles';
import { TableRow } from '@components/TableContents/styles';
import { MemoTableContentReview } from '@components/TableContents';
import { PropsReview } from '@utils/type';
import { VscReply } from 'react-icons/vsc';

type Props = {
  data: PropsReview;
};
const Review: VFC<Props> = ({ data }) => {
  const parentRef = useRef<HTMLDivElement>(null);
  const childRef = useRef<HTMLFormElement>(null);
  const comment = data?.comment;

  const onOpenCollapse = useCallback(() => {
    if (parentRef.current === null || childRef.current === null) return;

    if (parentRef.current.clientHeight > 0) {
      parentRef.current.style.height = '0';
      parentRef.current.style.marginBottom = '0';
    } else {
      parentRef.current.style.height = `${childRef.current.clientHeight}px`;
      parentRef.current.style.marginBottom = '3rem';
    }
  }, []);

  return (
    <>
      <TableRow onClick={onOpenCollapse}>
        <MemoTableContentReview data={data} />
      </TableRow>
      <FormWrapper ref={parentRef}>
        <ReviewForm ref={childRef}>
          <UserReview>{data.content}</UserReview>
          {comment ? (
            <>
              <VscReply />
              <Comment>{comment}</Comment>
            </>
          ) : (
            <>
              <VscReply />
              <Input />
              <Divider />
              <SubmitButton>등록</SubmitButton>
            </>
          )}
        </ReviewForm>
      </FormWrapper>
    </>
  );
};

export default Review;
