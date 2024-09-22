package org.javaexercises.petfrieds_transporte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entrega {
    private Long id;
    private LocalDate dataEnvio;
    private LocalDate dataPrevistaEntrega;
    private String statusEntrega;
    private Endereco endereco;
    private List<String> produtoId;
}

