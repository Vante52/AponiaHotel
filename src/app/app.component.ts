import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { User, Post, Comment } from './models/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  user: User | null = null;
  posts: Post[] = [];
  loading: boolean = false;
  error: string = '';

  constructor(private apiService: ApiService) {}

  // Método para manejar la búsqueda desde el componente hijo
  onSearchUser(username: string) {
    if (!username.trim()) return;

    this.loading = true;
    this.error = '';
    this.user = null;
    this.posts = [];

    this.apiService.getUserByUsername(username).subscribe({
      next: (users) => {
        if (users.length > 0) {
          this.user = users[0];
          this.loadUserPosts(users[0].id);
        } else {
          this.loading = false;
          this.error = 'Usuario no encontrado';
        }
      },
      error: (error) => {
        this.loading = false;
        this.error = 'Error buscando usuario';
      }
    });
  }

  private loadUserPosts(userId: number) {
    this.apiService.getPostsByUser(userId).subscribe({
      next: (posts) => {
        this.posts = posts;
        this.loading = false;
      },
      error: (error) => {
        this.loading = false;
        this.posts = [];
        this.error = 'Error cargando posts del usuario';
      }
    });
  }
}