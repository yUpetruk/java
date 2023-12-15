package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BundleTxtSerializer implements Serializer<Bundle> {

    @Override
    public String serialize(Bundle entity) {
        return entity.toString();
    }

    @Override
    public Bundle deserialize(String data, Class<Bundle> valueType) {
        Pattern pattern = Pattern.compile("Bundle\\{instruments=\\[(.*?)], discount=(.*?)}?$");
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            String instruments_string = matcher.group(1);
            float discount = Float.parseFloat(matcher.group(2));

            var instruments_split = instruments_string.split("}, ");
            var instruments = new HashSet<Product>();

            var productSerializer = new ProductTxtSerializer();
            for (var instrument : instruments_split) {
                instruments.add(productSerializer.deserialize(instrument, Product.class));
            }

            try {
                return new Bundle(instruments, discount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public void serialize(Bundle entity, File file) {
        String txt = serialize(entity);

        try {
            Files.write(file.toPath(), txt.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bundle deserialize(File file, Class<Bundle> valueType) {
        String txt;
        try {
            txt = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deserialize(txt, valueType);
    }
}
