package Product;

import Product.Interfaces.ShippableProduct;

import java.time.LocalDate;

public class TV implements ShippableProduct {

    private String name;
    private float price;
    private int quantity;
    private float weight;

    public TV(String name, float price, int quantity, float weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public float getWeight() {
        return weight;
    }

}
