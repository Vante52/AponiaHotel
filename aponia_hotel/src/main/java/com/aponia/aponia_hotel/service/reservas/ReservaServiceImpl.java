package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
import com.aponia.aponia_hotel.repository.reservas.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository repository;

    public ReservaServiceImpl(ReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reserva> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reserva> listarPorCliente(String clienteId) {
        return repository.findByClienteId(clienteId);
    }

    @Override
    public Reserva crear(Reserva reserva) {
        if (repository.existsByCodigo(reserva.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una reserva con ese código");
        }
        return repository.save(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reserva> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reserva> obtenerPorCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    @Override
    public Reserva actualizar(Reserva reserva) {
        Optional<Reserva> existente = repository.findById(reserva.getId());
        if (existente.isPresent() && !existente.get().getCodigo().equals(reserva.getCodigo())) {
            if (repository.existsByCodigo(reserva.getCodigo())) {
                throw new IllegalArgumentException("Ya existe una reserva con ese código");
            }
        }
        return repository.save(reserva);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public void confirmarReserva(String id) {

    }

    @Override
    public Reserva cancelarReserva(String id) {
        Optional<Reserva> reservaOpt = repository.findById(id);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            if ("completada".equals(reserva.getEstado())) {
                throw new IllegalStateException("No se puede cancelar una reserva completada");
            }
            reserva.setEstado("cancelada");
            return repository.save(reserva);
        }
        throw new IllegalArgumentException("No se encontró la reserva con ID: " + id);
    }
}