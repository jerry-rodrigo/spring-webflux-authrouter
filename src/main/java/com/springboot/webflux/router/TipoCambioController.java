package com.springboot.webflux.router;

import com.springboot.webflux.entity.TipoCambio;
import com.springboot.webflux.service.TipoCambioService;
import com.springboot.webflux.security.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/tipos-cambio")
public class TipoCambioController {

    private static final Logger log = LoggerFactory.getLogger(TipoCambioController.class);
    @Autowired
    private TipoCambioService tipoCambioService;

    @Autowired
    private JwtProvider jwtProvider;

    // Constructor para inyectar dependencias en el controlador
    public TipoCambioController(TipoCambioService tipoCambioService, JwtProvider jwtProvider) {
        this.tipoCambioService = tipoCambioService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/listar")
    public Flux<TipoCambio> obtenerTiposCambio() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMapMany(username -> tipoCambioService.obtenerTodos());
    }

    @GetMapping("listarId/{id}")
    public Mono<TipoCambio> obtenerTipoCambioPorId(@PathVariable Long id) {
        return tipoCambioService.obtenerPorId(id);
    }

    @PostMapping("/crear")
    public Mono<TipoCambio> crearTipoCambio(@RequestBody TipoCambio tipoCambio) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .doOnNext(username -> {
                    tipoCambio.setSolicitante(username);
                    tipoCambio.setTipoCambio(tipoCambio.getTipocambio());
                    tipoCambio.setFechasolicitud(LocalDateTime.now());
                })
                .flatMap(ignore -> tipoCambioService.crearTipoCambio(tipoCambio));
    }

    @PutMapping("actualizar/{id}")
    public Mono<TipoCambio> actualizarTipoCambio(@PathVariable Long id, @RequestBody TipoCambio tipoCambio) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .doOnNext(username -> {
                    tipoCambio.setSolicitante(username);
                    tipoCambio.setTipoCambio(tipoCambio.getTipocambio());
                    tipoCambio.setFechasolicitud(LocalDateTime.now());
                })
                .flatMap(ignore -> tipoCambioService.actualizarTipoCambio(id, tipoCambio));
    }

    @DeleteMapping("eliminar/{id}")
    public Mono<Void> eliminarTipoCambio(@PathVariable Long id) {
        return tipoCambioService.eliminarTipoCambio(id);
    }

    @GetMapping("/listarPorSolicitante")
    public Flux<TipoCambio> obtenerTiposCambioPorSolicitante() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMapMany(username -> tipoCambioService.obtenerPorSolicitante(username));
    }
}