package com.aponia.aponia_hotel.repository.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, String> {
    List<Pago> findByReservaId(String reservaId);
    List<Pago> findByReservaIdAndEstado(String reservaId, String estado);
}