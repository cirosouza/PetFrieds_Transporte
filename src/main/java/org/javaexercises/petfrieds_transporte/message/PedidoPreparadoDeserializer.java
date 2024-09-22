package org.javaexercises.petfrieds_transporte.message;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.javaexercises.petfrieds_transporte.event.PedidoPreparado;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PedidoPreparadoDeserializer extends StdDeserializer<PedidoPreparado> {

    public PedidoPreparadoDeserializer() {
        super(PedidoPreparado.class);
    }

    @Override
    public PedidoPreparado deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        PedidoPreparado eventoPedidoPreparado = null;

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        SimpleDateFormat formatoDataSimples = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        try {
            eventoPedidoPreparado = new PedidoPreparado(
                    node.get("pedidoId").asLong(),
                    formatoDataSimples.parse(node.get("momentoPreparacao").asText())
            );
        } catch (ParseException e) {
            throw new IOException("Formato de datos invalido", e);
        }

        return eventoPedidoPreparado;
    }
}
