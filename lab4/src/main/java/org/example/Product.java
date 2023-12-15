package org.example;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class Product {
    @NotNull
    private Type type;

    @Size(min=5, max=10,message="name size should be more than 5 symbols")
    private String name;

    @Min(value = 1)
    private float price;

    public Product() {
        type = null;
        name = null;
        price = 0;
    }

    public Product(Type type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static class ProductBuilder {
        private Type type;
        private String name;
        private float price;

        public ProductBuilder setType(Type type) {
            this.type = type;
            return this;
        }

        public ProductBuilder setPrice(float price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Product build() throws IllegalArgumentException {
            Product product = new Product(this.type, this.name, this.price);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (constraintViolations.isEmpty()) return product;

            StringBuilder exception = new StringBuilder();
            for (var constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exception.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("; ");
            }

            throw new IllegalArgumentException(exception.toString());
        }
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Float.compare(product.price, price) != 0) return false;
        if (type != product.type) return false;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (price != 0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
