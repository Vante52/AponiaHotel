package com.aponia.aponia_hotel.service.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.HabitacionTipo;
import com.aponia.aponia_hotel.repository.habitaciones.HabitacionTipoRepository;
import com.aponia.aponia_hotel.service.habitaciones.HabitacionTipoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionTipoServiceImpl implements HabitacionTipoService {

    private final HabitacionTipoRepository repository;

    public HabitacionTipoServiceImpl(HabitacionTipoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HabitacionTipo> listar() {
        return repository.findAll();
    }

    @Override
    public HabitacionTipo crear(HabitacionTipo habitacionTipo) {
        repository.save(habitacionTipo);
        return habitacionTipo;
    }

    @Override
    public Optional<HabitacionTipo> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public HabitacionTipo actualizar(HabitacionTipo habitacionTipo) {
        repository.update(habitacionTipo);
        return habitacionTipo;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}