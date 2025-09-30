import { Component, Input, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { User, Post, Comment } from '../../models/user.model';

@Component({ // ← Añadir este decorador
  selector: 'app-posts-usuario',
  templateUrl: './posts-usuario.component.html',
  styleUrls: ['./posts-usuario.component.css']
})
export class PostsUsuarioComponent implements OnInit {
  @Input() posts: Post[] = [];
  @Input() user: User | null = null;
  
  postComments: { [postId: number]: Comment[] } = {};
  loadingComments: { [postId: number]: boolean } = {};

  constructor(private apiService: ApiService) {}

  ngOnInit() {}

  // Cargar comentarios para un post específico
  loadCommentsForPost(postId: number) {
    this.loadingComments[postId] = true;
    
    this.apiService.getCommentsByPost(postId).subscribe({
      next: (comments) => {
        this.postComments[postId] = comments;
        this.loadingComments[postId] = false;
      },
      error: (error) => {
        this.loadingComments[postId] = false;
        console.error('Error cargando comentarios:', error);
      }
    });
  }

  // Verificar si un post tiene comentarios cargados
  hasComments(postId: number): boolean {
    return this.postComments[postId] && this.postComments[postId].length > 0;
  }

  // Obtener comentarios de un post
  getComments(postId: number): Comment[] {
    return this.postComments[postId] || [];
  }

  // Verificar si se están cargando comentarios
  isLoadingComments(postId: number): boolean {
    return this.loadingComments[postId] === true;
  }

  // Método para obtener el conteo de una reacción específica
  getReactionCount(reactions: any, reactionType: string): number {
    if (!reactions) return 0;
    
    if (typeof reactions === 'number') {
      // Si es número, solo mostrar para likes
      return reactionType === 'likes' ? reactions : 0;
    }
    
    if (typeof reactions === 'object' && reactions[reactionType]) {
      return reactions[reactionType];
    }
    
    return 0;
  }

  // Método para obtener el total de reacciones
  getTotalReactions(reactions: any): number {
    if (!reactions) return 0;
    
    if (typeof reactions === 'number') {
      return reactions;
    }
    
    if (typeof reactions === 'object') {
      return Object.values(reactions).reduce((sum: number, value: any) => {
        return sum + (typeof value === 'number' ? value : 0);
      }, 0);
    }
    
    return 0;
  }
}