package org.POO_AC2.dominio.recursos.Json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    public static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    }

    public static String stringfy(Object a) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

        return objectWriter.writeValueAsString(a);
    }

    public static void writeAllData(ArrayList<?> Array, File file) throws IOException {
        objectMapper.writeValue(file, Array);
    }

    public static <T> ArrayList<T> readAllData(File file, Class<T> objectType) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        ArrayList<T> list = objectMapper.readValue(fileInputStream, new TypeReference<ArrayList<T>>() {
        });

        return list;
    }
}
