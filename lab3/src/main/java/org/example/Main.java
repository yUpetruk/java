package org.example;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        var yamahaF310 = new Product(Type.String, "Yamaha F310", 6000);
        var yamahaF310case = new Product(Type.Accessories, "Yamaha F310 tuner professional case", 2500);
        var ibanezIGC10 = new Product(Type.Accessories, "IBANEZ IGC10 capo(acoustic)", 600);

        var set1 = new HashSet<Product>();
        set1.add(yamahaF310);
        set1.add(yamahaF310case);
        set1.add(ibanezIGC10);

        var ibanezRg421 = new Product(Type.String, "IBANEZ RG421 Electric", 16300);

        var bundle1 = new Bundle(set1, 5);

        var pickPack = new Product(Type.Accessories, "Guitar pick pack", 200);
        var tuner = new Product(Type.Accessories, "Guitar tuner", 300);
        var strap = new Product(Type.Accessories, "Guitar strap (leather)", 1200);

        var set2 = new HashSet<Product>();
        set2.add(pickPack);
        set2.add(tuner);
        set2.add(strap);

        var bundle2 = new Bundle(set2, 10);



        var shop = new Shop.ShopBuilder()
                .addBundle(bundle1)
                .addBundle(bundle2)
                .addInstrument(ibanezRg421)
                .addInstrument(ibanezIGC10)
                .build();



        var service1 = new ServiceComparable();
        var service2 = new ServiceStream();

        var productList = new ArrayList<Product>();
        productList.add(yamahaF310);
        productList.add(yamahaF310case);
        productList.add(ibanezIGC10);
        productList.add(ibanezRg421);

        System.out.println(service1.sortByPrice(productList));
        System.out.println(service2.sortByPrice(productList));

        var bundleList = new ArrayList<Bundle>();
        bundleList.add(bundle1);
        bundleList.add(bundle2);

        System.out.println(service1.sortByTotalPrice(bundleList));
        System.out.println(service2.sortByTotalPrice(bundleList));

        System.out.println(service1.sortByDiscount(bundleList));
        System.out.println(service2.sortByDiscount(bundleList));

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Products and bundles including keyword \"tuner\":");
//        service1.getProductsAndBundlesWithProductByPartName(shop, "tuner").forEach(System.out::println);
        service2.getProductsAndBundlesWithProductByPartName(shop, "tuner").forEach(System.out::println);
    }
}