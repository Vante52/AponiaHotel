package com.aponia.aponia_hotel.service.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario crear(Usuario usuario);
    Optional<Usuario> obtener(String id);
    Usuario actualizar(Usuario usuario);
    void eliminar(String id);
    Optional<Usuario> findByEmail(String email);
}