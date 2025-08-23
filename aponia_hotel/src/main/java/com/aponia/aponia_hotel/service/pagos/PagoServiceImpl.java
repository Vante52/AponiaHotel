package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import com.aponia.aponia_hotel.repository.pagos.PagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;

    public PagoServiceImpl(PagoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPorReserva(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPorReservaYEstado(String reservaId, String estado) {
        return repository.findByReservaIdAndEstado(reservaId, estado);
    }

    @Override
    public Pago crear(Pago pago) {
        return repository.save(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pago> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Pago actualizar(Pago pago) {
        return repository.save(pago);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public Pago completarPago(String id) {
        Optional<Pago> pagoOpt = repository.findById(id);
        if (pagoOpt.isPresent()) {
            Pago pago = pagoOpt.get();
            pago.setEstado("completado");
            return repository.save(pago);
        }
        throw new IllegalArgumentException("No se encontró el pago con ID: " + id);
    }
}