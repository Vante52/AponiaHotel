import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User, Post, Comment } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'https://dummyjson.com';

  constructor(private http: HttpClient) { }

  // USUARIOS
  getUsers(): Observable<User[]> {
    return this.http.get<any>(`${this.baseUrl}/users`).pipe(
      map(response => response.users)
    );
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/users/${id}`);
  }

  searchUsers(query: string): Observable<User[]> {
    return this.http.get<any>(`${this.baseUrl}/users/search?q=${query}`).pipe(
      map(response => response.users)
    );
  }

  // POSTS
  getPosts(): Observable<Post[]> {
    return this.http.get<any>(`${this.baseUrl}/posts`).pipe(
      map(response => response.posts)
    );
  }

  getPostsByUser(userId: number): Observable<Post[]> {
    return this.http.get<any>(`${this.baseUrl}/posts/user/${userId}`).pipe(
      map(response => response.posts)
    );
  }

  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}/posts/${id}`);
  }

  // COMENTARIOS
  getComments(): Observable<Comment[]> {
    return this.http.get<any>(`${this.baseUrl}/comments`).pipe(
      map(response => response.comments)
    );
  }

  getCommentsByPost(postId: number): Observable<Comment[]> {
    return this.http.get<any>(`${this.baseUrl}/comments/post/${postId}`).pipe(
      map(response => response.comments)
    );
  }

  getCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.baseUrl}/comments/${id}`);
  }

  // Filtrar usuarios por cualquier campo
  filterUsers(key: string, value: string): Observable<User[]> {
    return this.http.get<any>(`${this.baseUrl}/users/filter?key=${key}&value=${value}`).pipe(
      map(response => response.users)
    );
  }

  // filtrar por username
  getUserByUsername(username: string): Observable<User[]> {
    return this.filterUsers('username', username);
  }
}