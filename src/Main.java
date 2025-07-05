import Customer.Customer;
import Product.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        //  Products
        Cheese cheese = new Cheese("Gouda", 20.0f, 5, 300, LocalDate.of(2025, 8, 1));
        Cheese expiredCheese = new Cheese("Old Gouda", 15.0f, 2, 250, LocalDate.of(2023, 1, 1));
        Biscuit biscuit = new Biscuit("Digestive", 10.0f, 4, LocalDate.of(2025, 8, 1));
        Biscuit expiredBiscuit = new Biscuit("Old Biscuit", 8.0f, 3, LocalDate.of(2022, 1, 1));
        TV tv = new TV("Samsung 55in", 500.0f, 1, 12000);
        MobileScratchCard scratchCard = new MobileScratchCard("Vodafone 10 EGP", 10.0f, 10);

        //  Customers
        Customer richCustomer = null;
        Customer poorCustomer = null;

        try {
            richCustomer = new Customer("Ali", 2000);
            poorCustomer = new Customer("Amr", 50);
        } catch (Exception e) {
            System.out.println("Failed to create customer: " + e.getMessage());
        }

        System.out.println("\n CASE 1:  Successful Checkout");
        try {
            richCustomer.add(cheese, 2);
            richCustomer.add(tv, 1);
            richCustomer.checkout(); // Should succeed
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n CASE 2:  Adding Expired Product");
        try {
            richCustomer.add(expiredBiscuit, 1);
            richCustomer.checkout(); // Should throw expired exception
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n CASE 3:  Out of Stock");
        try {
            richCustomer.add(tv, 1); // Already bought, stock = 0
            richCustomer.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n CASE 4:  Insufficient Balance");
        try {
            poorCustomer.add(cheese, 3); // 3 x 20 = 60, balance = 50
            poorCustomer.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n CASE 5: Adding Digital Product (No Shipping)");
        try {
            richCustomer.add(scratchCard, 2); // Digital item
            richCustomer.checkout(); // Should succeed
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n CASE 6:  Negative Quantity");
        try {
            richCustomer.add(cheese, -1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n MAIN EXECUTION COMPLETED");

    }
}