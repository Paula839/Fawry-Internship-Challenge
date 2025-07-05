package Product;

import Product.Interfaces.Product;

public class MobileScratchCard implements Product {
    private String name;
    private float price;
    private int quantity;

    public MobileScratchCard(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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
}
