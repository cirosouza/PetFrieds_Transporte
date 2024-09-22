package org.javaexercises.petfrieds_transporte.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import lombok.RequiredArgsConstructor;
import org.javaexercises.petfrieds_transporte.event.PedidoPreparado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private static final Logger LOG = LoggerFactory.getLogger(TransporteService.class);

    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Autowired
    JacksonPubSubMessageConverter jacksonPubSubMessageConverter;

    @ServiceActivator(inputChannel = "inputMessageChannel")
    private void receber(PedidoPreparado payloadPedidoPreparado,
                         @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<PedidoPreparado> message) {

        LOG.info("========= Mensagem Recebida -----> " + payloadPedidoPreparado);
        message.ack();
        this.processarPedidoPreparado(payloadPedidoPreparado);
    }

    private void processarPedidoPreparado(PedidoPreparado eventoPedidoPreparado) {
        LOG.info("========= Iniciando Transporte dos Itens do Pedido ID: -----> " + eventoPedidoPreparado.getPedidoId());
    }
}


