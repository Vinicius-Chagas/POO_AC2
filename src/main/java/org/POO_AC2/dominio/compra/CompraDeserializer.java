package org.POO_AC2.dominio.compra;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.recursos.Json.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompraDeserializer extends StdDeserializer<Compra> {

    public CompraDeserializer() {
        this(null);
    }

    public CompraDeserializer(Class<Compra> vc) {
        super(vc);
    }

    @Override
    public Compra deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String dataCompra = node.get("dataCompra").asText();
        double valorTotal = node.get("valorTotal").asDouble();
        int parcelasTotais = node.get("parcelasTotais").asInt();
        int parcelasPagas = node.get("parcelasPagas").asInt();
        JsonNode nodeUltimoPagemento = node.get("ultimoPagamento");
        String ultimoPagamento =null;
        if(nodeUltimoPagemento != null && !nodeUltimoPagemento.isNull()){
            ultimoPagamento = nodeUltimoPagemento.asText();
        }

        Long id = node.get("id").asLong();
        JsonNode clienteNode = node.get("cliente");
        Cliente cliente = Json.fromJson(clienteNode, Cliente.class);
        JsonNode itensCompraNode = node.get("itensCompra");
        List<ItemCompra> itensCompra = new ArrayList<>();

        if (itensCompraNode.isArray()) {
            for (JsonNode itemNode : itensCompraNode) {
                // Verifica se o item é um objeto (não uma string "java.util.ArrayList")
                if (itemNode.isArray()) {
                    // Se o nó for um array, itere sobre seus elementos
                    for (JsonNode innerNode : itemNode) {
                        if(innerNode.isObject()){
                            ItemCompra itemCompra = Json.fromJson(innerNode, ItemCompra.class);
                            itensCompra.add(itemCompra);
                        }
                    }
                }
            }
        }


        return new Compra(dataCompra, valorTotal, cliente, itensCompra, parcelasTotais, parcelasPagas, ultimoPagamento, id);
    }
}

