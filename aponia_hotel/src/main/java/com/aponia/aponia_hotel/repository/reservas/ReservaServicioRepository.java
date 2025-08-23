package com.aponia.aponia_hotel.repository.reservas;

import com.aponia.aponia_hotel.entities.reservas.ReservaServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaServicioRepository extends JpaRepository<ReservaServicio, String> {
    List<ReservaServicio> findByReservaId(String reservaId);
    List<ReservaServicio> findByServicioIdAndFecha(String servicioId, LocalDate fecha);
}