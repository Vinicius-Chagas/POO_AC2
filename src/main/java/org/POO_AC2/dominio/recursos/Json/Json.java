package org.POO_AC2.dominio.recursos.Json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    // Esta função instancia um Objectmapper, classe do Jackson que mapeia um objeto e o trasforma em Json. Foi feita uma função para instanciar o objeto por causa das configurações que ele pode carregar.
    public static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY); // Configuração para guardar o tipo do objeto no json

        return defaultObjectMapper;
    }

    // Parse Json pega uma string em formato json e transforma em um Hashmap chave valor.
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException { // Transforma um Jsom em um objeto da classe passada
        return objectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    } // Transforma um objeto em Json

    public static void stringfy(Object a) throws JsonProcessingException { // Printa um objeto como string
        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

        System.out.println(objectWriter.writeValueAsString(a));
    }

    public static void writeAllData(ArrayList<?> Array, File file) throws IOException { // Escreve um array de objetos de um determinado tipo em um arquivo
        objectMapper.writeValue(file, Array);
    }

    public static <T> ArrayList<T> readAllData(File file, Class<T> objectType) throws IOException { // lê um arquivo e instancia um array de determinado tipo
        FileInputStream fileInputStream = new FileInputStream(file);

        ArrayList<T> list = objectMapper.readValue(fileInputStream, new TypeReference<ArrayList<T>>() {
        });

        return list;
    }
}
