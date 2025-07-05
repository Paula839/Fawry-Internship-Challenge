# Fawry Full Stack Development Internship Challenge

This repository contains my solution for the **Fawry Full Stack Development Internship Challenge**. The challenge focuses on designing and implementing a modular and object-oriented **e-commerce system** in Java.

---

## 🧠 Problem Overview

The task was to simulate an e-commerce system with the following core requirements:

- Support multiple **product types**, each with different behaviors:
  - **Shippable** products (e.g., TVs, Mobiles)
  - **Expirable** products (e.g., Cheese, Biscuits)
  - Products that are both or neither
- Allow **customers** to:
  - Add products to their cart (with quantity and stock constraints)
  - Perform **checkout** operations with balance and expiration validation
- Design a **Shipping Service** that accepts only valid `ShippableProduct` items
- Handle edge cases like:
  - Expired products
  - Out-of-stock items
  - Insufficient customer balance

---

## 💻 Technologies Used

- **Java 17**
- Object-Oriented Programming (OOP)
- IntelliJ IDEA
- JUnit for unit testing (optional extension)

---

## 🧱 Project Structure

Fawry-Internship-Challenge-main/
├── .idea/
├── out/
├── src/
│   ├── Cart/
│   │   ├── Cart.java
│   │   ├── CartItem.java
│   │   └── ShippingService.java
│   ├── Customer/
│   │   └── Customer.java
│   ├── Product/
│   │   ├── Interfaces/
│   │   │   ├── Shippable.java
│   │   │   └── Expirable.java
│   │   ├── Biscuit.java
│   │   ├── Cheese.java
│   │   ├── Mobile.java
│   │   ├── MobileScratchCard.java
│   │   └── TV.java
│   └── Main.java
├── test/
│   ├── Cart/
│   │   └── CartTest.java
│   └── Customer/
│       └── CustomerTest.java
├── Fawry Full Stack Development Internship Challenge.iml



---

## 🧪 Features & Scenarios

- ✅ Add product to cart with stock validation
- ✅ Check expiration status during checkout
- ✅ Deduct balance and update stock post-checkout
- ✅ Validate product type before shipping
- ✅ Display detailed order summary

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Paula839/Fawry-Internship-Challenge.git
   cd Fawry-Internship-Challenge
javac Main.java
java Main
