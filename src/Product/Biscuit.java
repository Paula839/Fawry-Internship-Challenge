package Product;

import Product.Interfaces.ExpirableProduct;

import java.time.LocalDate;

public class Biscuit implements ExpirableProduct {
    private String name;
    private float price;
    private int quantity;
    private LocalDate expireDate;
    public Biscuit(String name, float price, int quantity, LocalDate expireDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expireDate = expireDate;
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
    public LocalDate getExpiryDate() {
        return expireDate;
    }
}
