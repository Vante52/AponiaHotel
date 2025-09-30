import { Component, Input } from '@angular/core';
import { User } from '../../models/user.model';

@Component({ // ← Añadir este decorador
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent {
  @Input() user: User | null = null;
}