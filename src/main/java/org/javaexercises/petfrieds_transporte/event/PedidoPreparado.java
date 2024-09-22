package org.javaexercises.petfrieds_transporte.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class PedidoPreparado implements Serializable {
    private Long pedidoId;
    private Date momentoPreparacao;
}
