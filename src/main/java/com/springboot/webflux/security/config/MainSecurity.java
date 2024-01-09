package com.springboot.webflux.security.config;

import com.springboot.webflux.security.repository.SecurityContextRepository;
import com.springboot.webflux.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class MainSecurity {

    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
        return http
                .authorizeExchange()
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/api/tipos-cambio/crear").hasAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.GET, "/api/tipos-cambio/listar", "/api/tipos-cambio/listarId/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .pathMatchers(HttpMethod.PUT, "/api/tipos-cambio/actualizar/**").hasAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/api/tipos-cambio/eliminar/**").hasAuthority("ROLE_ADMIN")
                .anyExchange().authenticated()
                .and()
                .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
                .securityContextRepository(securityContextRepository)
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .csrf().disable()
                .build();
    }
}
