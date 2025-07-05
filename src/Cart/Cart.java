package Cart;

import Product.Interfaces.ExpirableProduct;
import Product.Interfaces.Product;
import Product.Interfaces.ShippableProduct;

import java.security.Key;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart  {

    private List<CartItem> products;
    private Map<String, Integer> index;

    //8 laptop
    public Cart() {
        products = new ArrayList<>();
        index = new HashMap<>();
    }

    public void add(Product product, int quantity)  {



        if(index.containsKey(product.getName())) {
            int idx = index.get(product.getName());
            int oldQuantity = products.get(idx).getQuantity();
            products.set(idx, (new CartItem(product, quantity + oldQuantity)));
        }

        else {
            index.put(product.getName(), products.size());
            products.add(new CartItem(product, quantity));
        }
    }


    public float checkout(float balance) throws Exception {
        //1-Cart is empty
        if(products.isEmpty()) {
            throw new Exception("Cart is empty!");
        }


        float total = calculateSubtotal();
        float fees = ShippingService.shipProducts(getShippableProducts());
        balance -= total + fees;

        //2-Insufficient balance
        if(balance < 0) {
            throw new Exception("Insufficient balance");
        }

        clear();

        printReceipt(total, fees, balance);

        return balance;
    }


    private float calculateSubtotal() throws Exception {
        float total = 0.0f;
        for(CartItem cartItem: products) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            //3- Out of stock
            if(quantity > product.getQuantity()) {
                throw new Exception("Out of stock!");
            }

            if(product instanceof ExpirableProduct) {
                ExpirableProduct expirableProduct = (ExpirableProduct) product;
                //4- expired
                if(expirableProduct.isExpired()) {
                    throw new Exception("Date Expired!");
                }
            }

            total += quantity * product.getPrice();
        }

        return total;
    }

    List<ShippableProduct> getShippableProducts() {
        List<ShippableProduct> shippableProducts = new ArrayList<>();
        for(CartItem cartItem: products) {
            Product product = cartItem.getProduct();
            if(product instanceof ShippableProduct) {
                ShippableProduct shippableProduct = (ShippableProduct) product;
                shippableProducts.add(shippableProduct);
            }
        }

        return shippableProducts;
    }

    void clear() {

        for(CartItem cartItem : products) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            int remainingQuantity = product.getQuantity() - quantity;
            product.setQuantity(remainingQuantity);
        }

        products.clear();
        index.clear();
    }

    private void printReceipt(float subtotal, float shipping, float balance) {
        System.out.println("** Checkout receipt **");
        for (CartItem cartItem : products) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            System.out.println(quantity + "x " + product.getName() + " " + (product.getPrice() * quantity));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shipping);
        System.out.println("Amount " + (subtotal + shipping));
        System.out.println("Balance left: " + balance);
        System.out.println("\n");
    }

}
