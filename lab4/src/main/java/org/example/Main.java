package org.example;

import java.io.File;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        var yamahaF310 = new Product(Type.String, "Yamaha F310", 6000);
        var yamahaF310case = new Product(Type.Accessories, "Yamaha F310 professional case", 2500);
        var ibanezIGC10 = new Product(Type.Accessories, "IBANEZ IGC10 capo(acoustic)", 600);

        try {
            yamahaF310 = new Product.ProductBuilder()
                    .setType(Type.String)
                    .setName("aaaaa").setPrice(4123)
                    .build();
            System.out.println(yamahaF310);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Bundle bundle;

        try {
            bundle = new Bundle.BundleBuilder()
                    .addInstrument(yamahaF310case)
                    .setDiscount(0)
                    .build();

            System.out.println(bundle);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}