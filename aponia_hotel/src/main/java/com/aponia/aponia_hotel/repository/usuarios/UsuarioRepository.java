package com.aponia.aponia_hotel.repository.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Usuario> findAll() {
        return jdbc.query("SELECT * FROM usuarios", new BeanPropertyRowMapper<>(Usuario.class));
    }

    public Optional<Usuario> findById(String id) {
        return jdbc.query("SELECT * FROM usuarios WHERE id = ?", 
                new BeanPropertyRowMapper<>(Usuario.class), id)
                .stream().findFirst();
    }

    public int save(Usuario usuario) {
        return jdbc.update(
            "INSERT INTO usuarios (id, email, password_hash, rol) VALUES (?, ?, ?, ?)",
            usuario.getId(), usuario.getEmail(), usuario.getPasswordHash(), usuario.getRol()
        );
    }

    public int update(Usuario usuario) {
        return jdbc.update(
            "UPDATE usuarios SET email = ?, password_hash = ?, rol = ? WHERE id = ?",
            usuario.getEmail(), usuario.getPasswordHash(), usuario.getRol(), usuario.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM usuarios WHERE id = ?", id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return jdbc.query("SELECT * FROM usuarios WHERE email = ?", 
                new BeanPropertyRowMapper<>(Usuario.class), email)
                .stream().findFirst();
    }
}