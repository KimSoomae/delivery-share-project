export interface PropsReview {
  id: string;
  src: string;
  alt: string;
  content: string;
  nickname: string;
  stars: number;
  comment?: string;
}

export interface PropsOrder {
  date: string;
  location: string;
  state: string;
  price: string;
  nickname: string;
  id: string;
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
