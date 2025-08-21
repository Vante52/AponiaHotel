package com.aponia.aponia_hotel.repository.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Repository
public class PagoRepository {
    private final JdbcTemplate jdbc;

    public PagoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Pago> findAll() {
        return jdbc.query("SELECT * FROM pagos", new BeanPropertyRowMapper<>(Pago.class));
    }

    public Optional<Pago> findById(String id) {
        return jdbc.query("SELECT * FROM pagos WHERE id = ?", 
                new BeanPropertyRowMapper<>(Pago.class), id)
                .stream().findFirst();
    }

    public int save(Pago pago) {
        return jdbc.update(
            "INSERT INTO pagos (id, reserva_id, tipo, monto, fecha, metodo_pago, estado, concepto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            pago.getId(), pago.getReserva().getId(), pago.getTipo(),
            pago.getMonto(), pago.getFecha(), pago.getMetodoPago(),
            pago.getEstado(), pago.getConcepto()
        );
    }

    public int update(Pago pago) {
        return jdbc.update(
            "UPDATE pagos SET reserva_id = ?, tipo = ?, monto = ?, fecha = ?, metodo_pago = ?, estado = ?, concepto = ? WHERE id = ?",
            pago.getReserva().getId(), pago.getTipo(), pago.getMonto(),
            pago.getFecha(), pago.getMetodoPago(), pago.getEstado(),
            pago.getConcepto(), pago.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM pagos WHERE id = ?", id);
    }

    public List<Pago> findByReservaId(String reservaId) {
        return jdbc.query("SELECT * FROM pagos WHERE reserva_id = ?", 
                new BeanPropertyRowMapper<>(Pago.class), reservaId);
    }

    public int updateEstado(String id, String estado) {
        return jdbc.update("UPDATE pagos SET estado = ? WHERE id = ?", estado, id);
    }

    public BigDecimal sumMontoByReservaIdAndEstado(String reservaId, String estado) {
        String sql = "SELECT COALESCE(SUM(monto), 0) FROM pagos WHERE reserva_id = ? AND estado = ?";
        return jdbc.queryForObject(sql, BigDecimal.class, reservaId, estado);
    }
}