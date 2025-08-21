package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
import com.aponia.aponia_hotel.repository.reservas.ReservaRepository;
import com.aponia.aponia_hotel.service.reservas.ReservaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository repository;

    public ReservaServiceImpl(ReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reserva> listar() {
        return repository.findAll();
    }

    @Override
    public Reserva crear(Reserva reserva) {
        repository.save(reserva);
        return reserva;
    }

    @Override
    public Optional<Reserva> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Reserva actualizar(Reserva reserva) {
        repository.update(reserva);
        return reserva;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Reserva> findByClienteId(String clienteId) {
        return repository.findByClienteId(clienteId);
    }

    @Override
    public Optional<Reserva> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    @Override
    public void confirmarReserva(String id) {
        repository.updateEstado(id, "confirmada");
    }

    @Override
    public void cancelarReserva(String id) {
        repository.updateEstado(id, "cancelada");
    }
}