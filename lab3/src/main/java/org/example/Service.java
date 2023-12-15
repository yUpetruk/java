package org.example;

import java.time.Period;
import java.util.List;

public interface Service {
    List<Product> sortByPrice(List<Product> products);
    List<Bundle> sortByTotalPrice(List<Bundle> bundles);
    List<Bundle> sortByDiscount(List<Bundle> bundle);
    List<Product> getProductsAndBundlesWithProductByPartName(Shop shop, String string);
}
