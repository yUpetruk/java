package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductTxtSerializer implements Serializer<Product>{
    @Override
    public String serialize(Product entity) {
        return entity.toString();
    }

    @Override
    public Product deserialize(String data, Class<Product> valueType) {
        Pattern pattern = Pattern.compile("Product\\{type=(.*?), name='(.*?)', price=(.*?), manufactureDate=(\\d{4}-\\d{2}-\\d{2})}?$");
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            Type type = Type.valueOf(matcher.group(1));
            String name = matcher.group(2);
            float price = Float.parseFloat(matcher.group(3));
            LocalDate date = LocalDate.parse(matcher.group(4));

            try {
                return new Product.ProductBuilder()
                        .setType(type)
                        .setName(name)
                        .setPrice(price)
                        .setManufactureDate(date)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public void serialize(Product entity, File file) {
        String txt = serialize(entity);

        try {
            Files.write(file.toPath(), txt.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product deserialize(File file, Class<Product> valueType) {
        String txt;
        try {
            txt = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deserialize(txt, valueType);
    }
}
