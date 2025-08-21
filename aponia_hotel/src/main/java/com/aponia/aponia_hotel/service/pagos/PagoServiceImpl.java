    package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import com.aponia.aponia_hotel.repository.pagos.PagoRepository;
import com.aponia.aponia_hotel.service.pagos.PagoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;

    public PagoServiceImpl(PagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pago> listar() {
        return repository.findAll();
    }

    @Override
    public Pago crear(Pago pago) {
        repository.save(pago);
        return pago;
    }

    @Override
    public Optional<Pago> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Pago actualizar(Pago pago) {
        repository.update(pago);
        return pago;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Pago> findByReservaId(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    public void completarPago(String id) {
        repository.updateEstado(id, "completado");
    }

    @Override
    public BigDecimal obtenerTotalPagado(String reservaId) {
        return repository.sumMontoByReservaIdAndEstado(reservaId, "completado");
    }
}