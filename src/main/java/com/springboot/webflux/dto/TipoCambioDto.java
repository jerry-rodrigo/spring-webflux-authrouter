package com.springboot.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoCambioDto {

    @NotBlank(message = "Ingrese el monto a convertir")
    private Double monto;
    @NotBlank(message = "Ingrese la moneda de origen")
    private String monedaorigen;
    @NotBlank(message = "Ingrese la moneda de destino")
    private String monedadestino;
    @NotBlank(message = "Ingrese el tipo de cambio")
    private Double tipocambio;
}
