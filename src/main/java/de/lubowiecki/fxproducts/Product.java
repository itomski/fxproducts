package de.lubowiecki.fxproducts;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {

    private String name;
    private String description;
    private int count;
    private LocalDate availableSince;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getAvailableSince() {
        return availableSince;
    }

    public void setAvailableSince(LocalDate availableSince) {
        this.availableSince = availableSince;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", count=").append(count);
        sb.append(", availableSince=").append(availableSince);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
