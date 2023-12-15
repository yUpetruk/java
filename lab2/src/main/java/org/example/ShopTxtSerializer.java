package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopTxtSerializer implements Serializer<Shop> {


    @Override
    public String serialize(Shop entity) {
        return entity.toString();
    }

    @Override
    public Shop deserialize(String data, Class<Shop> valueType) {
        Pattern pattern = Pattern.compile("Shop\\{instruments=\\[(.*?)], bundles=\\[(.*?)]}?$");
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            var instruments = new HashSet<Product>();
            var instruments_split = matcher.group(1).split("}, ");
            var productTxtSerializer = new ProductTxtSerializer();
            for (var instrument: instruments_split) {
                instruments.add((productTxtSerializer.deserialize(instrument, Product.class)));
            }

            var bundles = new HashSet<Bundle>();
            var bundles_split = matcher.group(2).split("}, Bundle");
            var bundleSerializer = new BundleTxtSerializer();
            for (var bundle: bundles_split) {
                bundles.add(bundleSerializer.deserialize(bundle, Bundle.class));
            }

            try {
                var shopBuilder = new Shop.ShopBuilder();
                for (var bundle : bundles) {
                    shopBuilder.addBundle(bundle);
                }
                for (var instrument : instruments) {
                    shopBuilder.addInstrument(instrument);
                }

                return shopBuilder.build();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public void serialize(Shop entity, File file) {
        String txt = serialize(entity);

        try {
            Files.write(file.toPath(), txt.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Shop deserialize(File file, Class<Shop> valueType) {
        String txt;
        try {
            txt = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deserialize(txt, valueType);
    }
}
