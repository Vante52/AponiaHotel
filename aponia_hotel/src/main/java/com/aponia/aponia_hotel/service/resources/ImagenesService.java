package com.aponia.aponia_hotel.service.resources;

import com.aponia.aponia_hotel.entities.imagenes.Imagen;
import java.util.List;
import java.util.Optional;

public interface ImagenService {
    List<Imagen> listar();
    Imagen crear(Imagen imagen);
    Optional<Imagen> obtener(String id);
    Imagen actualizar(Imagen imagen);
    void eliminar(String id);
    List<Imagen> findByServicioId(String servicioId);
    List<Imagen> findByTipoHabitacionId(String tipoHabitacionId);
}