package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;

public class XmlSerializer<T> implements Serializer<T> {

    private final XmlMapper xmlMapper;

    public XmlSerializer() {
        xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String serialize(T entity) {
        try {
            return xmlMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(String data, Class<T> valueType) {
        try {
            return xmlMapper.readValue(data, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void serialize(T entity, File file) {
        String xmlString = serialize(entity);

        try {
            Files.write(file.toPath(), xmlString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(File file, Class<T> valueType) {
        String xmlString;
        try {
            xmlString = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deserialize(xmlString, valueType);
    }
}
