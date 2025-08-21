package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.ReservaServicio;
import com.aponia.aponia_hotel.repository.reservas.ReservaServicioRepository;
import com.aponia.aponia_hotel.service.reservas.ReservaServicioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicioServiceImpl implements ReservaServicioService {

    private final ReservaServicioRepository repository;

    public ReservaServicioServiceImpl(ReservaServicioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReservaServicio> listar() {
        return repository.findAll();
    }

    @Override
    public ReservaServicio crear(ReservaServicio reservaServicio) {
        repository.save(reservaServicio);
        return reservaServicio;
    }

    @Override
    public Optional<ReservaServicio> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public ReservaServicio actualizar(ReservaServicio reservaServicio) {
        repository.update(reservaServicio);
        return reservaServicio;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<ReservaServicio> findByReservaId(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    public List<ReservaServicio> findByServicioId(String servicioId) {
        return repository.findByServicioId(servicioId);
    }
}