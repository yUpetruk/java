package org.example;

import java.time.LocalDate;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        var yamahaF310 = new Product.ProductBuilder()
                .setType(Type.String)
                .setName("Yamaha F310")
                .setPrice(6000)
                .setManufactureDate(LocalDate.now())
                .build();

        var yamahaF310case = new Product.ProductBuilder()
                .setType(Type.Accessories)
                .setName("Yamaha F310 professional case")
                .setPrice(2500)
                .setManufactureDate(LocalDate.now())
                .build();
        var ibanezIGC10 = new Product.ProductBuilder()
                .setType(Type.Accessories)
                .setName("IBANEZ IGC10 capo(acoustic)")
                .setPrice(2500)
                .setManufactureDate(LocalDate.now())
                .build();

        var set1 = new HashSet<Product>();
        set1.add(yamahaF310);
        set1.add(yamahaF310case);
        set1.add(ibanezIGC10);

        var ibanezRg421 = new Product.ProductBuilder()
                .setType(Type.String)
                .setName("IBANEZ RG421 Electric")
                .setPrice(16300)
                .setManufactureDate(LocalDate.now())
                .build();

        var bundle1 = new Bundle(set1, 20);

        System.out.println(bundle1);
        System.out.println(ibanezRg421);


        var shop = new Shop.ShopBuilder()
                .addBundle(bundle1)
                .addInstrument(ibanezRg421)
                .build();

        System.out.println(shop);
    }
}