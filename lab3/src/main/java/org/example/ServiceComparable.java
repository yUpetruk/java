package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceComparable implements Service {
    @Override
    public List<Product> sortByPrice(List<Product> products) {
        var result = new ArrayList<>(products);
        result.sort(Comparator.comparing(Product::getPrice));
        return result;
    }

    @Override
    public List<Bundle> sortByTotalPrice(List<Bundle> bundles) {
        var result = new ArrayList<>(bundles);
        result.sort(Comparator.comparing(Bundle::totalPrice));
        return  result;
    }

    @Override
    public List<Bundle> sortByDiscount(List<Bundle> bundles) {
        var result = new ArrayList<>(bundles);
        result.sort(Comparator.comparing(Bundle::getDiscount));
        return result;
    }

    @Override
    public List<Product> getProductsAndBundlesWithProductByPartName(Shop shop, String string) {
        var list = new ArrayList<Product>();
        for (var product: shop.getInstruments()) {
            if (product.getName().toLowerCase().contains(string.toLowerCase()))
                list.add(product);
        }

        for (var bundle: shop.getBundle()) {
            for (var product: bundle.getInstruments()) {
                if (product.getName().toLowerCase().contains(string.toLowerCase())) {
                    list.add(product);
                }
            }
        }

        return list;
    }
}
