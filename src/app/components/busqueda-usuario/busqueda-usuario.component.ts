import { Component, Output, EventEmitter } from '@angular/core';

@Component({ // ← Añadir este decorador
  selector: 'app-busqueda-usuario',
  templateUrl: './busqueda-usuario.component.html',
  styleUrls: ['./busqueda-usuario.component.css']
})
export class BusquedaUsuarioComponent {
  username: string = '';

  @Output() search = new EventEmitter<string>();

  onSearch() {
    if (this.username.trim()) {
      this.search.emit(this.username.trim());
    }
  }

  onKeyPress(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.onSearch();
    }
  }
}