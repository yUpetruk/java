package org.example;

import java.util.HashSet;
import java.util.Set;

public class Shop {
    private Set<Product> instruments;
    private Set<Bundle> bundles;

    private Shop() { }
    private Shop(Set<Product> instruments, Set<Bundle> bundles) {
        this.instruments = instruments;
        this.bundles = bundles;
    }

    public void setBundles(Set<Bundle> bundles) {
        this.bundles = bundles;
    }

    public void setInstruments(Set<Product> instruments) {
        this.instruments = instruments;
    }

    public Set<Bundle> getBundles() {
        return bundles;
    }

    public Set<Product> getInstruments() {
        return instruments;
    }

    public Set<Bundle> getBundle() {
        return bundles;
    }

    public void addInstrument(Product instrument) {
        instruments.add(instrument);
    }

    public void addBundle(Bundle bundle) {
        bundles.add(bundle);
    }

    public static class ShopBuilder {
        private Set<Product> instruments = new HashSet<>();
        private Set<Bundle> bundles = new HashSet<>();

        public ShopBuilder addInstrument(Product instrument) {
            this.instruments.add(instrument);
            return this;
        }

        public ShopBuilder addBundle(Bundle bundle) {
            this.bundles.add(bundle);
            return this;
        }

        public Shop build() {
            return new Shop(this.instruments, this.bundles);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (!instruments.equals(shop.instruments)) return false;
        return bundles.equals(shop.bundles);
    }

    @Override
    public int hashCode() {
        int result = instruments.hashCode();
        result = 31 * result + bundles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "instruments=" + instruments +
                ", bundles=" + bundles +
                '}';
    }
}
