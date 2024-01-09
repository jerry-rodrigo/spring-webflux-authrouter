package com.springboot.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tipos_cambio")
public class TipoCambio {
    @Id
    private Long id;
    private Double monto;
    private String monedaorigen;
    private String monedadestino;
    private Double montocontipocambio;
    private Double tipocambio;
    private String solicitante;

    @CreatedDate
    private LocalDateTime fechasolicitud;

    public void setTipoCambio(Double tipoCambio) {
        tipocambio = tipoCambio;
        // Calcula el montoConTipoCambio al establecer el tipoCambio
        montocontipocambio = (monto * tipoCambio);
    }
}
