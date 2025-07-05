package Customer;
import Customer.Customer;
import Product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    Customer customer;
    Cheese cheese;
    TV tv;
    Mobile mobile;
    Biscuit biscuit;
    MobileScratchCard scratchCard;
    Cheese expiredCheese;

    @BeforeEach
    void setup() throws Exception {
        customer = new Customer("Ali", 3000f);
        cheese = new Cheese("Cheddar", 5.99f, 3, 227f, LocalDate.of(2025, 7, 5));
        tv = new TV("Samsung", 799.99f, 2, 12000f);
        mobile = new Mobile("iPhone", 999.0f, 4, 500f);
        biscuit = new Biscuit("Oreo", 2.49f, 6, LocalDate.of(2025, 8, 15));
        scratchCard = new MobileScratchCard("Vodafone", 10.0f, 20);
        expiredCheese = new Cheese("Expired", 4.5f, 3, 200f, LocalDate.of(2022, 1, 1));
    }

    @Test
    void testSuccessfulCheckout() throws Exception {
        customer.add(cheese, 1);
        customer.add(scratchCard, 2);
        float newBalance = customer.checkout();
        assertTrue(newBalance < 3000f);
    }

    @Test
    void testCheckoutWithExpiredProduct() throws Exception {
        customer.add(expiredCheese, 1);
        assertThrows(Exception.class, customer::checkout);
    }

    @Test
    void testCheckoutWithOutOfStock() throws Exception {
        customer.add(tv, 3); // Only 2 in stock
        assertThrows(Exception.class, customer::checkout);
    }

    @Test
    void testCheckoutWithInsufficientBalance() throws Exception {
        Customer poorCustomer = new Customer("Poor", 50f);
        poorCustomer.add(mobile, 1); // 999 EGP
        assertThrows(Exception.class, poorCustomer::checkout);
    }

    @Test
    void testCheckoutWithEmptyCart() throws Exception {
        Customer emptyCartCustomer = new Customer("Empty", 1000f);
        assertThrows(Exception.class, emptyCartCustomer::checkout);
    }

    @Test
    void testAddWithNegativeQuantityThrowsException() {
        assertThrows(Exception.class, () -> customer.add(tv, -1));
    }
}