package com.aponia.aponia_hotel.service.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import com.aponia.aponia_hotel.repository.usuarios.UsuarioRepository;
import com.aponia.aponia_hotel.service.usuarios.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public Usuario crear(Usuario usuario) {
        repository.save(usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        repository.update(usuario);
        return usuario;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}