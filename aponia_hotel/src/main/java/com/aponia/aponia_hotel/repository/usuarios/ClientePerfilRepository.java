package com.aponia.aponia_hotel.repository.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.ClientePerfil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientePerfilRepository {
    private final JdbcTemplate jdbc;

    public ClientePerfilRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ClientePerfil> findAll() {
        return jdbc.query("SELECT * FROM clientes_perfil", new BeanPropertyRowMapper<>(ClientePerfil.class));
    }

    public Optional<ClientePerfil> findById(String usuarioId) {
        return jdbc.query("SELECT * FROM clientes_perfil WHERE usuario_id = ?", 
                new BeanPropertyRowMapper<>(ClientePerfil.class), usuarioId)
                .stream().findFirst();
    }

    public int save(ClientePerfil clientePerfil) {
        return jdbc.update(
            "INSERT INTO clientes_perfil (usuario_id, nombre_completo, telefono, fecha_registro) VALUES (?, ?, ?, ?)",
            clientePerfil.getUsuarioId(), clientePerfil.getNombreCompleto(), 
            clientePerfil.getTelefono(), clientePerfil.getFechaRegistro()
        );
    }

    public int update(ClientePerfil clientePerfil) {
        return jdbc.update(
            "UPDATE clientes_perfil SET nombre_completo = ?, telefono = ?, fecha_registro = ? WHERE usuario_id = ?",
            clientePerfil.getNombreCompleto(), clientePerfil.getTelefono(), 
            clientePerfil.getFechaRegistro(), clientePerfil.getUsuarioId()
        );
    }

    public int deleteById(String usuarioId) {
        return jdbc.update("DELETE FROM clientes_perfil WHERE usuario_id = ?", usuarioId);
    }
}