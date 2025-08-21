package com.aponia.aponia_hotel.service.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.ClientePerfil;
import java.util.List;
import java.util.Optional;

public interface ClientePerfilService {
    List<ClientePerfil> listar();
    ClientePerfil crear(ClientePerfil clientePerfil);
    Optional<ClientePerfil> obtener(String usuarioId);
    ClientePerfil actualizar(ClientePerfil clientePerfil);
    void eliminar(String usuarioId);
}