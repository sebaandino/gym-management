package com.gym.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RutinaDiaDto {
    private String dia;
    private List<EjercicioDto> ejercicios;

}
