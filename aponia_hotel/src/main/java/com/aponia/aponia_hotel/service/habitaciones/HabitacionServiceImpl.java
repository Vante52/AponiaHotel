package com.aponia.aponia_hotel.service.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.Habitacion;
import com.aponia.aponia_hotel.repository.HabitacionRepository;
import com.aponia.aponia_hotel.service.HabitacionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository repository;

    public HabitacionServiceImpl(HabitacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Habitacion> listar() {
        return repository.findAll();
    }

    @Override
    public Habitacion crear(Habitacion habitacion) {
        repository.save(habitacion);
        return habitacion;
    }

    @Override
    public Optional<Habitacion> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Habitacion actualizar(Habitacion habitacion) {
        repository.update(habitacion);
        return habitacion;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Habitacion> findByTipoId(String tipoId) {
        return repository.findByTipoId(tipoId);
    }

    @Override
    public List<Habitacion> findDisponiblesByTipoAndFechas(String tipoId, String checkIn, String checkOut) {
        return repository.findDisponiblesByTipoAndFechas(tipoId, checkIn, checkOut);
    }
}