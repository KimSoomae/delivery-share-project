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

export interface PropsOrder {
  seq: number;
  status: string;
  sum: number;
  call: Call;
}

type Call = {
  __typename: string;
  request_R: string;
  request_call: string;
  callLocation: CallLocation;
  price: number;
  user: User;
  calltext: string;
  created_at: string;
  cart: Cart;
};

type CallLocation = {
  __typename: string;
  place: string;
};

type Cart = {
  __typename: string;
  selected_menu: SelectedMenu;
};

type SelectedMenu = {
  __typename: string;
  menu: Menu;
};

type Menu = {
  __typename: string;
  name: string;
  price: number;
};

export interface PropsMenu {
  id: string;
  src: string;
  alt: string;
  name: string;
  category: string;
  price: string;
  desc: string;
}
