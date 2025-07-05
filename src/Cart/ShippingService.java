package Cart;

import Product.Interfaces.ShippableProduct;

import java.util.List;

public class ShippingService {
    public static float shipProducts(List<ShippableProduct> products) {
        System.out.println("\n** Shipment notice **");
        float fees = 0.0f;
        float costPerGram = 0.05f;
        float totalWeight = 0.0f;
        for(ShippableProduct product : products) {
            float weight = product.getWeight();
            totalWeight += weight;
            fees += weight * costPerGram;
            System.out.println(product.getQuantity() + "x " + product.getName() + " " + weight + "g");
        }

        if (totalWeight >= 1000) {
            System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000.0);
        }

        else {
            System.out.printf("Total package weight %fg%n", totalWeight);
        }

        return fees;
    }
}
