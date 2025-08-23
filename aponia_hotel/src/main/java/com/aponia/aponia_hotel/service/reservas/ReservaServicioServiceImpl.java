package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.ReservaServicio;
import com.aponia.aponia_hotel.repository.reservas.ReservaServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaServicioServiceImpl implements ReservaServicioService {

    private final ReservaServicioRepository repository;

    public ReservaServicioServiceImpl(ReservaServicioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaServicio> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaServicio> listarPorReserva(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaServicio> listarPorServicioYFecha(String servicioId, LocalDate fecha) {
        return repository.findByServicioIdAndFecha(servicioId, fecha);
    }

    @Override
    public ReservaServicio crear(ReservaServicio reservaServicio) {
        return repository.save(reservaServicio);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservaServicio> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public ReservaServicio actualizar(ReservaServicio reservaServicio) {
        return repository.save(reservaServicio);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}