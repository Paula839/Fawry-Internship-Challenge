package Customer;

import Cart.Cart;
import Product.Interfaces.Product;

public class Customer {
    private static int id = 1;
    private int customerID;
    private String name;
    private float balance;

    Cart cart;
    public Customer(String name, float balance) throws Exception {
        if(name.length() < 2) {
            throw new Exception("Name should be atleast 2 characters: " + name);
        }

        if(balance < 0) {
            throw new Exception("Quantity cannot be negative: " + balance);
        }

        this.name = name;
        this.balance = balance;
        cart = new Cart();
        customerID = id++;
        System.out.println("Customer No. " + customerID + " Has been created successfully");
    }


    public void add(Product product) throws Exception {
        add(product, 1);
    }

    public void add(Product product, int quantity) throws Exception {
        if(quantity <= 0) {
            throw new Exception("Quantity cannot be negative: " + quantity);
        }

        cart.add(product, quantity);
        System.out.println(quantity + " Products " + product.getName() + " have been added to the cart successfully");
    }

    public float checkout() throws Exception {

        this.balance = cart.checkout(balance);
        System.out.println("Checkout has been done successfully");
        return this.balance;
    }


}
