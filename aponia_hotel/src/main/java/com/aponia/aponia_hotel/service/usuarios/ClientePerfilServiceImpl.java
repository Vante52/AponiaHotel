package com.aponia.aponia_hotel.service.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.ClientePerfil;
import com.aponia.aponia_hotel.repository.ClientePerfilRepository;
import com.aponia.aponia_hotel.service.ClientePerfilService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePerfilServiceImpl implements ClientePerfilService {

    private final ClientePerfilRepository repository;

    public ClientePerfilServiceImpl(ClientePerfilRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientePerfil> listar() {
        return repository.findAll();
    }

    @Override
    public ClientePerfil crear(ClientePerfil clientePerfil) {
        repository.save(clientePerfil);
        return clientePerfil;
    }

    @Override
    public Optional<ClientePerfil> obtener(String usuarioId) {
        return repository.findById(usuarioId);
    }

    @Override
    public ClientePerfil actualizar(ClientePerfil clientePerfil) {
        repository.update(clientePerfil);
        return clientePerfil;
    }

    @Override
    public void eliminar(String usuarioId) {
        repository.deleteById(usuarioId);
    }
}