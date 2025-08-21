package com.aponia.aponia_hotel.service.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.HabitacionTipo;
import java.util.List;
import java.util.Optional;

public interface HabitacionTipoService {
    List<HabitacionTipo> listar();
    HabitacionTipo crear(HabitacionTipo habitacionTipo);
    Optional<HabitacionTipo> obtener(String id);
    HabitacionTipo actualizar(HabitacionTipo habitacionTipo);
    void eliminar(String id);
}