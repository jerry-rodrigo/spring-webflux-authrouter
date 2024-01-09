package com.springboot.webflux.service;

import com.springboot.webflux.entity.TipoCambio;
import com.springboot.webflux.repository.TipoCambioReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TipoCambioService {

    private final TipoCambioReactiveRepository tipoCambioRepository;

    // Constructor para inyectar el TipoCambioReactiveRepository
    public TipoCambioService(TipoCambioReactiveRepository tipoCambioRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
    }

    public Flux<TipoCambio> obtenerTodos() {
        return tipoCambioRepository.findAll();
    }

    public Mono<TipoCambio> obtenerPorId(Long id) {
        return tipoCambioRepository.findById(id);
    }

    public Mono<TipoCambio> crearTipoCambio(TipoCambio tipoCambio) {
        return tipoCambioRepository.save(tipoCambio);
    }

    public Mono<TipoCambio> actualizarTipoCambio(Long id, TipoCambio tipoCambio) {
        return tipoCambioRepository.findById(id)
                .flatMap(existingTipoCambio -> {
                    existingTipoCambio.setMonto(tipoCambio.getMonto());
                    existingTipoCambio.setMonedaorigen(tipoCambio.getMonedaorigen());
                    existingTipoCambio.setMonedadestino(tipoCambio.getMonedadestino());
                    existingTipoCambio.setTipoCambio(tipoCambio.getTipocambio());
                    existingTipoCambio.setMontocontipocambio(tipoCambio.getMontocontipocambio());
                    existingTipoCambio.setSolicitante(tipoCambio.getSolicitante());
                    existingTipoCambio.setFechasolicitud(tipoCambio.getFechasolicitud());
                    return tipoCambioRepository.save(existingTipoCambio);
                });
    }

    public Mono<Void> eliminarTipoCambio(Long id) {
        return tipoCambioRepository.deleteById(id);
    }

    public Flux<TipoCambio> obtenerPorSolicitante(String solicitante) {
        return tipoCambioRepository.findBySolicitante(solicitante);
    }

    public static TipoCambio createEmpty() {
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setId(-1L);
        return tipoCambio;
    }
}
