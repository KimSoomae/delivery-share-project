export interface PropsReview {
  seq: string;
  createdAt: string;
  images?: Image[];
  content: string;
  rate: number;
  reply?: Reply;
  resseq: number;
  user?: User;
}

type Image = {
  __typename: string;
  image: string;
};

type Reply = {
  __typename: string;
  content: string;
};

type User = {
  __typename: string;
  ID: string;
};

type temporalMenuType = {
  id: string;
  name: string;
  count: number;
};

type temporalRequestsType = {
  id: string;
  nickname: string;
  content: string;
};

export interface PropsOrder {
  date?: string;
  location?: string;
  status: string;
  menus?: temporalMenuType[];
  requests?: temporalRequestsType[];
  price?: string;
  nickname?: string;
  seq: string;
}

export interface PropsMenu {
  id: string;
  src: string;
  alt: string;
  name: string;
  category: string;
  price: string;
  desc: string;
}
