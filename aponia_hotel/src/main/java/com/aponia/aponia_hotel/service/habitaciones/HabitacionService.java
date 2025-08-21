package com.aponia.aponia_hotel.service.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.Habitacion;
import java.util.List;
import java.util.Optional;

public interface HabitacionService {
    List<Habitacion> listar();
    Habitacion crear(Habitacion habitacion);
    Optional<Habitacion> obtener(String id);
    Habitacion actualizar(Habitacion habitacion);
    void eliminar(String id);
    List<Habitacion> findByTipoId(String tipoId);
    List<Habitacion> findDisponiblesByTipoAndFechas(String tipoId, String checkIn, String checkOut);
}