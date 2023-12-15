package org.example;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class Bundle {
    @NotNull
    @Size(min = 2, message="size must be greater than or equal to {min}")
    private Set<Product> instruments;
    @Min(value = 1)
    private float discount;

    public Bundle() {
        instruments = new HashSet<Product>();
        discount = 0;
    }

    public Bundle(Set<Product> instruments, float discount) {
        this.instruments = instruments;
        this.discount = discount;
    }

    public static class BundleBuilder {
        private Set<Product> instruments = new HashSet<>();
        private float discount;

        public BundleBuilder setInstruments(Set<Product> instruments) {
            this.instruments = instruments;
            return this;
        }

        public BundleBuilder addInstrument(Product instrument) {
            this.instruments.add(instrument);
            return this;
        }

        public BundleBuilder setDiscount(float discount) {
            this.discount = discount;
            return this;
        }

        public Bundle build() throws IllegalArgumentException {
            Bundle bundle = new Bundle(this.instruments, this.discount);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Bundle>> constraintViolations = validator.validate(bundle);

            if (constraintViolations.isEmpty()) return bundle;

            StringBuilder exception = new StringBuilder();
            for (var constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exception.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("; ");
            }

            throw new IllegalArgumentException(exception.toString());
        }

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
