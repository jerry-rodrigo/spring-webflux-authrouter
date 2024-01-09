package com.springboot.webflux.repository;

import com.springboot.webflux.entity.TipoCambio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TipoCambioReactiveRepository extends ReactiveCrudRepository<TipoCambio, Long> {
    Flux<TipoCambio> findBySolicitante(String solicitante);
}
