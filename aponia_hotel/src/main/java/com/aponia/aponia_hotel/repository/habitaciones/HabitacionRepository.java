package com.aponia.aponia_hotel.repository.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, String> {
    List<Habitacion> findByTipoIdAndActivaIsTrue(String tipoId);
    List<Habitacion> findByActivaIsTrue();
    boolean existsByNumero(Integer numero);

    List<Habitacion> findHabitacionesDisponibles(String id, Boolean checkIn, Boolean checkOut);
}