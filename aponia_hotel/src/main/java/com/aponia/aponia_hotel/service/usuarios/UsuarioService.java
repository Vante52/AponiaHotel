package com.aponia.aponia_hotel.service.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    /**
     * Lista todos los usuarios
     */
    List<Usuario> listar();

    /**
     * Crea un nuevo usuario
     */
    Usuario crear(Usuario usuario);

    /**
     * Obtiene un usuario por su ID
     */
    Optional<Usuario> obtener(String id);

    /**
     * Actualiza un usuario existente
     */
    Usuario actualizar(Usuario usuario);

    /**
     * Elimina un usuario por su ID
     */
    void eliminar(String id);

    /**
     * Obtiene un usuario por su email
     */
    Optional<Usuario> obtenerPorEmail(String email);

    // Métodos deprecados o no utilizados
    // @deprecated Usar obtenerPorEmail() en su lugar
    // Optional<Usuario> findByEmail(String email);
}