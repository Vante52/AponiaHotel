package com.aponia.aponia_hotel.service;

import com.aponia.aponia_hotel.entities.Usuario;
import com.aponia.aponia_hotel.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public void crear(Usuario u) {
        repo.save(u);
    }

    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }
}
