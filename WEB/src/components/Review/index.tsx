import React, { useCallback, useEffect, useRef, useState, VFC } from 'react';
import {
  Input,
  FormWrapper,
  ReviewForm,
  SubmitButton,
  Comment,
  Divider,
  UserReview,
  ReviewImage,
} from '../Review/styles';
import { TableRow } from '@components/TableContents/styles';
import { MemoTableContentReview } from '@components/TableContents';
import { PropsReview } from '@utils/type';
import { VscReply } from 'react-icons/vsc';
import { useMutation } from '@apollo/client';
import { POST_REPLY } from '@Apollo/mutations';
import { v4 as uuid } from 'uuid';

type Props = {
  info: PropsReview;
};

const Review: VFC<Props> = ({ info }) => {
  const parentRef = useRef<HTMLDivElement>(null);
  const childRef = useRef<HTMLFormElement>(null);
  const commentSeq = info.seq;
  const images = info?.images;

  const [writeReply, { data }] = useMutation(POST_REPLY);
  const [comment, setComment] = useState(info.reply?.content);
  const [input, setInput] = useState('');
  const [refresh, setRefresh] = useState(comment ? true : false);

  useEffect(() => {
    if (input) setComment(input);
  }, [refresh]);

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

  const handleInput = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setInput(e.target.value);
  };

  const handleSubmit = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();

    writeReply({
      variables: {
        content: input,
        resReviewSeq: +commentSeq,
      },
    });

    setRefresh(prev => !prev);
  };

  return (
    <>
      <TableRow onClick={onOpenCollapse}>
        <MemoTableContentReview data={info} refresh={refresh} />
      </TableRow>
      <FormWrapper ref={parentRef}>
        <ReviewForm ref={childRef}>
          {images?.map(image => (
            <ReviewImage key={uuid()} src={image.image} />
          ))}
          <UserReview>{info.content}</UserReview>
          {comment ? (
            <>
              <VscReply />
              <Comment>{comment}</Comment>
            </>
          ) : (
            <>
              <VscReply />
              <Input onChange={e => handleInput(e)} />
              <Divider />
              <SubmitButton onClick={e => handleSubmit(e)}>등록</SubmitButton>
            </>
          )}
        </ReviewForm>
      </FormWrapper>
    </>
  );
};

export default Review;
