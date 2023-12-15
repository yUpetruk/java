package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private Type type;
    private String name;
    private float price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;

    public Product() { }

    private Product(Type type, String name, float price, LocalDate manufactureDate) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.manufactureDate = manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static class ProductBuilder {
        private Type type;
        private String name;
        private float price;
        private LocalDate manufactureDate;

        ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        ProductBuilder setType(Type type) {
            this.type = type;
            return this;
        }

        ProductBuilder setPrice(float price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setManufactureDate(LocalDate manufactureDate) {
            this.manufactureDate = manufactureDate;
            return this;
        }

        Product build() {
            return  new Product(this.type, this.name, this.price, this.manufactureDate);
        }

    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manufactureDate=" + manufactureDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Float.compare(product.price, price) != 0) return false;
        if (type != product.type) return false;
        if (!Objects.equals(name, product.name)) return false;
        return Objects.equals(manufactureDate, product.manufactureDate);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (manufactureDate != null ? manufactureDate.hashCode() : 0);
        return result;
    }
}
