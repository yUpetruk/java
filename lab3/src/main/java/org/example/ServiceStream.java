package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceStream implements Service  {
    @Override
    public List<Product> sortByPrice(List<Product> products) {
        return products.stream().sorted(((o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bundle> sortByTotalPrice(List<Bundle> bundles) {
        return bundles.stream().sorted((o1, o2) -> Float.compare(o1.totalPrice(), o2.totalPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bundle> sortByDiscount(List<Bundle> bundle) {
        return bundle.stream().sorted((o1, o2) -> Float.compare(o1.getDiscount(), o2.getDiscount()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsAndBundlesWithProductByPartName(Shop shop, String string) {
        ArrayList<Product> list = new ArrayList<>();
        list.addAll(
                shop.getInstruments()
                        .stream()
                        .filter((i -> i.getName().toLowerCase().contains(string.toLowerCase())))
                        .toList()
        );

        shop.getBundle().forEach(bundle -> {
            list.addAll(bundle.getInstruments()
                    .stream()
                    .filter((i -> i.getName().toLowerCase().contains(string.toLowerCase())))
                    .toList());
        });


        return list;
    }
}
