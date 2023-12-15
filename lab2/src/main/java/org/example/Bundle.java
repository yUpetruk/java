package org.example;

import java.util.HashSet;
import java.util.Set;

public class Bundle {
    private Set<Product> instruments;
    private float discount;

    public Bundle() {
        instruments = new HashSet<Product>();
        discount = 0;
    }

    public Bundle(Set<Product> instruments, float discount) {
        this.instruments = instruments;
        this.discount = discount;
    }

    public void setInstruments(Set<Product> instruments) {
        this.instruments = instruments;
    }

    public float getDiscount() {
        return discount;
    }

    public Set<Product> getInstruments() {
        return instruments;
    }

    public void addInstrument(Product instrument) {
        instruments.add(instrument);
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float calculateTotalPrice() {
        float total = calculateTotalPriceNoDiscount();
        return total - (total * (discount / 100));
    }

    public float calculateTotalPriceNoDiscount() {
        float total = 0;
        for (Product instrument: instruments) {
            total += instrument.getPrice();
        }

        return total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bundle bundle = (Bundle) o;

        if (Float.compare(bundle.discount, discount) != 0) return false;
        return instruments.equals(bundle.instruments);
    }

    @Override
    public int hashCode() {
        int result = instruments.hashCode();
        result = 31 * result + (discount != +0.0f ? Float.floatToIntBits(discount) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "instruments=" + instruments +
                ", discount=" + discount +
                '}';
    }
}
