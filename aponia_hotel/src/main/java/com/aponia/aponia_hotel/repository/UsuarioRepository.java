package com.aponia.aponia_hotel.repository;

import com.aponia.aponia_hotel.entities.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Usuario> findAll() {
        return jdbc.query("SELECT * FROM usuarios", new BeanPropertyRowMapper<>(Usuario.class));
    }

    public int save(Usuario u) {
        return jdbc.update(
            "INSERT INTO usuarios (id, email, password_hash, estado, rol) VALUES (?, ?, ?, ?, ?)",
            u.getId(), u.getEmail(), u.getPasswordHash(), u.getEstado(), u.getRol()
        );
    }

    public Usuario findByEmail(String email) {
        return jdbc.queryForObject(
            "SELECT * FROM usuarios WHERE email = ?",
            new BeanPropertyRowMapper<>(Usuario.class),
            email
        );
    }
}
