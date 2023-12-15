package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper;

    public JsonSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String serialize(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(String data, Class<T> valueType) {
        try {
            return objectMapper.readValue(data, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void serialize(T entity, File file) {
        String jsonString = serialize(entity);

        try {
            Files.write(file.toPath(), jsonString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(File file, Class<T> valueType) {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deserialize(jsonString, valueType);
    }
}