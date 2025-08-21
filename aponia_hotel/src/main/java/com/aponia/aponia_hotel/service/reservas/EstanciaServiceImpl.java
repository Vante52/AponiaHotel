package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Estancia;
import com.aponia.aponia_hotel.repository.reservas.EstanciaRepository;
import com.aponia.aponia_hotel.service.reservas.EstanciaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstanciaServiceImpl implements EstanciaService {

    private final EstanciaRepository repository;

    public EstanciaServiceImpl(EstanciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Estancia> listar() {
        return repository.findAll();
    }

    @Override
    public Estancia crear(Estancia estancia) {
        repository.save(estancia);
        return estancia;
    }

    @Override
    public Optional<Estancia> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Estancia actualizar(Estancia estancia) {
        repository.update(estancia);
        return estancia;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Estancia> findByReservaId(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    public void asignarHabitacion(String estanciaId, String habitacionId) {
        repository.updateHabitacionAsignada(estanciaId, habitacionId);
    }
}