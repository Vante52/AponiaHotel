import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { BusquedaUsuarioComponent } from './components/busqueda-usuario/busqueda-usuario.component';
import { PerfilUsuarioComponent } from './components/perfil-usuario/perfil-usuario.component';
import { PostsUsuarioComponent } from './components/posts-usuario/posts-usuario.component';
import { TotalReaccionesPipe } from './pipes/total-reacciones.pipe';

@NgModule({
  declarations: [
    AppComponent,
    BusquedaUsuarioComponent,
    PerfilUsuarioComponent,
    PostsUsuarioComponent,
    TotalReaccionesPipe,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }