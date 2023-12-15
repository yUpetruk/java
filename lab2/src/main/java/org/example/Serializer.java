package org.example;

import java.io.File;

public interface Serializer<T> {

    String serialize(T entity);

    T deserialize(String data, Class<T> valueType);

    void serialize(T entity, File file);

    T deserialize(File file, Class<T> valueType);
}
