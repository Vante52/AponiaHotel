export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  image: string;
  username: string;
  phone?: string;
  age?: number;
  gender?: string;
}

export interface Post {
  id: number;
  title: string;
  body: string;
  userId: number;
  tags: string[];
  reactions: number;
  views?: number;
}

export interface Comment {
  id: number;
  body: string;
  postId: number;
  user: {
    id: number;
    username: string;
  };
}