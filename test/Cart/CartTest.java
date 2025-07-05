package Cart;

import Product.Biscuit;
import Product.Cheese;
import Product.TV;
import Product.Interfaces.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Product cheese;
    private Product expiredBiscuit;
    private Product tv;

    @BeforeEach
    void setup() {
        cart = new Cart();
        cheese = new Cheese("Gouda", 20.0f, 5, 300, LocalDate.of(2025, 8, 1));
        expiredBiscuit = new Biscuit("Old Biscuit", 10.0f, 3, LocalDate.of(2023, 1, 1));
        tv = new TV("Samsung", 500.0f, 2, 12000);
    }

    @Test
    void testAddValidProductToCart() throws Exception {
        cart.add(cheese, 2);
        // Product quantity is not changed yet
        System.out.println(cheese.getQuantity());
        assertEquals(5, cheese.getQuantity());

        cart.checkout(1000);
        System.out.println(cheese.getQuantity());
        assertEquals(3, cheese.getQuantity());
    }

    @Test
    void testAddProductExceedingStock() throws Exception {
        cart.add(cheese, 3);
        cart.add(cheese, 3); // Total = 6, but stock = 5

        Exception e = assertThrows(Exception.class, () -> {
            cart.checkout(1000);
        });
        assertNotNull(e);
    }

    @Test
    void testCheckoutEmptyCartThrowsException() {
        Exception e = assertThrows(Exception.class, () -> {
            cart.checkout(1000);
        });
        assertNotNull(e);
    }

    @Test
    void testCheckoutWithExpiredProductThrowsException() throws Exception {
        cart.add(expiredBiscuit, 1);
        Exception e = assertThrows(Exception.class, () -> {
            cart.checkout(1000);
        });
        assertNotNull(e);
    }

    @Test
    void testCheckoutWithValidProducts() throws Exception {
        cart.add(cheese, 2); // 2 × 20 = 40
        cart.add(tv, 1);     // 1 × 500 = 500

        float startingBalance = 1200.0f; // should be enough to cover subtotal + shipping
        float newBalance = cart.checkout(startingBalance);

        float expectedSubtotal = 40 + 500;
        float expectedShipping = (300 + 12000) * 0.05f; // 615.0
        float expectedTotal = expectedSubtotal + expectedShipping;

        assertEquals(startingBalance - expectedTotal, newBalance, 0.001);

        // Verify stock decreased
        assertEquals(3, cheese.getQuantity());
        assertEquals(1, tv.getQuantity());
    }

}
