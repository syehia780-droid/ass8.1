# 🍽️ Restaurant Order Manager

A console-based **Restaurant Order Management System** developed in Java to practice and demonstrate the use of Java Collections.

The project focuses on using the appropriate collection for each responsibility:

* `ArrayList` → Restaurant Menu
* `LinkedList` → Kitchen Order Queue
* `HashMap` → Permanent Order Storage and Fast Lookup
* `LinkedHashMap` → Completed Orders in Completion Order

The project also demonstrates the use of **custom objects, enums, searching, adding, removing, queue processing, and order lifecycle management**.

---

## 🎯 Project Goal

The main goal of this project is to understand how different Java Collections can be selected and used according to the requirements of a real-world application.

The system manages:

* Restaurant menu items
* Customer orders
* Order items and quantities
* Kitchen order queue
* Order statuses
* Completed orders
* Cancelled orders

---

## 🧠 Learning Objectives

Through this project, I practiced:

* Using `ArrayList` to manage menu items.
* Using `LinkedList` to implement a kitchen queue.
* Using `HashMap` for fast order lookup using order ID.
* Using `LinkedHashMap` to preserve completed order order.
* Working with custom objects inside Collections.
* Adding, removing, searching, and displaying collection data.
* Using `enum` to represent order status.
* Managing an order through different lifecycle states.
* Automatically calculating order totals.
* Handling invalid IDs and empty collections.
* Understanding why a specific Collection is suitable for a specific task.

---

## 🏗️ Project Structure

The project contains the following main classes:

```text
Restaurant Order Manager
│
├── MenuItem
├── OrderItem
├── Order
├── OrderStatus
└── Restaurant
```

---

## 📦 Classes

### 1. MenuItem

Represents an item available in the restaurant menu.

#### Attributes

```java
int id
String name
double price
String category
```

#### Responsibilities

* Store menu item information.
* Provide getters and setters.
* Display item information using `toString()`.

---

### 2. OrderItem

Represents an item added to a customer order.

#### Attributes

```java
MenuItem item
int quantity
```

#### Responsibilities

* Store the selected menu item.
* Store the requested quantity.
* Calculate the subtotal.

The subtotal is calculated as:

```text
Subtotal = Item Price × Quantity
```

---

### 3. Order

Represents a customer's restaurant order.

#### Attributes

```java
int orderId
String customerName
ArrayList<OrderItem> items
double total
OrderStatus status
```

#### Responsibilities

* Add items to an order.
* Remove items from an order.
* Calculate the total.
* Display order information.
* Update the order status.

### Automatic Total Calculation

The order total is automatically updated whenever an item is added or removed.

For example:

```java
addItem()
    ↓
calculateTotal()
```

and:

```java
removeItem()
    ↓
calculateTotal()
```

Therefore, the `total` field always contains the current order total.

`displayOrder()` only displays the already calculated total.

---

## 🔄 OrderStatus Enum

The order status is represented using an enum instead of plain `String` values.

```java
PENDING
IN_KITCHEN
COMPLETED
CANCELLED
```

### Order Lifecycle

```text
Create Order
     ↓
  PENDING
     ↓
Add to Kitchen Queue
     ↓
 IN_KITCHEN
     ↓
Process Order
     ↓
 COMPLETED
```

An order can also be cancelled before completion:

```text
PENDING / IN_KITCHEN
          ↓
      CANCELLED
```

---

## 🏪 Restaurant Class

The `Restaurant` class manages the main collections and contains the main restaurant operations.

### Collections Used

```java
ArrayList<MenuItem> menu;
LinkedList<Order> kitchenQueue;
HashMap<Integer, Order> orders;
LinkedHashMap<Integer, Order> completedOrders;
```

---

## 📚 Why These Collections?

| Collection      | Purpose          | Why?                                                       |
| --------------- | ---------------- | ---------------------------------------------------------- |
| `ArrayList`     | Store menu items | Good for list-style storage and indexed access             |
| `LinkedList`    | Kitchen queue    | Suitable for processing orders from the front of the queue |
| `HashMap`       | Store all orders | Provides fast lookup using order ID                        |
| `LinkedHashMap` | Completed orders | Preserves insertion/completion order                       |

### ArrayList

The restaurant menu uses:

```java
ArrayList<MenuItem>
```

because menu items are stored as a list and can be accessed using an index.

### LinkedList

The kitchen uses:

```java
LinkedList<Order>
```

because orders must be processed in **First-In, First-Out (FIFO)** order.

```text
First order added
      ↓
First order processed
```

### HashMap

All orders are stored in:

```java
HashMap<Integer, Order>
```

The order ID is used as the key.

This allows the restaurant to quickly find an order:

```java
orders.get(orderId);
```

The HashMap is the **permanent record** of all orders.

Orders are never removed from it, even after completion or cancellation.

### LinkedHashMap

Completed orders are stored in:

```java
LinkedHashMap<Integer, Order>
```

Unlike a regular `HashMap`, `LinkedHashMap` preserves insertion order.

Therefore, completed orders can be displayed in the same order in which they were completed.

---

## 📋 Main Menu

The application provides the following operations:

```text
1. Add Menu Item
2. Remove Menu Item
3. Display Menu
4. Search Menu Item
5. Create Order
6. Add Item to Order
7. Remove Item from Order
8. Display Order
9. Add Order to Kitchen Queue
10. Process Next Order
11. Search Order
12. Check Order Status
13. Display Completed Orders
14. Cancel Order
15. Exit
```

---

## 🛒 Menu Operations

### Add Menu Item

The user enters:

* Item ID
* Name
* Price
* Category

The item is then added to the `ArrayList`.

Menu item IDs must be unique.

### Remove Menu Item

A menu item can be removed using its ID.

If the item does not exist, an appropriate message is displayed.

### Search Menu Item

The menu can be searched using the item ID.

---

## 🧾 Order Operations

### Create Order

The user enters:

* Order ID
* Customer Name

A new order is created with:

```text
Status = PENDING
```

The order is immediately stored in the `HashMap`.

---

### Add Item to Order

The system:

1. Finds the order using the order ID.
2. Finds the menu item using the item ID.
3. Reads the quantity.
4. Creates an `OrderItem`.
5. Adds it to the order.
6. Automatically recalculates the total.

Completed and cancelled orders cannot be modified.

---

### Remove Item from Order

The system finds the order and removes the requested item.

After removing the item, the order total is automatically recalculated.

---

## 👨‍🍳 Kitchen Queue

When an order is added to the kitchen:

```text
PENDING
   ↓
IN_KITCHEN
```

The order is added to:

```java
LinkedList<Order> kitchenQueue
```

An order cannot be added to the kitchen queue more than once.

---

## 🔥 Process Next Order

The first order in the kitchen queue is processed first.

The system checks:

1. Is the kitchen queue empty?
2. Does the order contain at least one item?

An empty order cannot be processed.

If processing is successful:

```text
IN_KITCHEN
    ↓
COMPLETED
```

The order is then:

* Removed from `kitchenQueue`
* Added to `completedOrders`
* Kept inside `orders`

---

## 🔎 Search Order

Orders can be searched using their ID through the `HashMap`.

This works regardless of the order status:

```text
PENDING
IN_KITCHEN
COMPLETED
CANCELLED
```

The order remains available in the HashMap throughout its entire lifecycle.

---

## 🚦 Check Order Status

The user enters an order ID.

The system searches for the order and displays its current:

```java
OrderStatus
```

---

## ✅ Completed Orders

Completed orders are stored in:

```java
LinkedHashMap<Integer, Order>
```

They are displayed in the same order in which they were completed.

For example:

```text
Order 101 → Completed
Order 105 → Completed
Order 103 → Completed
```

The system displays them in:

```text
101
105
103
```

---

## ❌ Cancel Order

An order can be cancelled if it has not already been completed or cancelled.

### If the order is PENDING

The order is simply changed to:

```text
CANCELLED
```

No queue operation is required.

### If the order is IN_KITCHEN

The order is:

1. Removed from `kitchenQueue`.
2. Changed to `CANCELLED`.

### If the order is COMPLETED

Cancellation is rejected.

### If the order is already CANCELLED

Cancellation is rejected.

The cancelled order remains in the `HashMap`.

It is **never added** to `completedOrders`.

---

## 🔐 Important Rules

The system follows these rules:

* Menu item IDs must be unique.
* Order IDs must be unique.
* The `HashMap` keeps every order ever created.
* Completed orders are never removed from the `HashMap`.
* Cancelled orders are never removed from the `HashMap`.
* An order cannot be processed if the kitchen queue is empty.
* An order with no items cannot be processed.
* An order cannot be processed twice.
* An item cannot be added if it does not exist in the menu.
* Completed orders cannot be modified.
* Cancelled orders cannot be modified.
* An order cannot be cancelled twice.
* A completed order cannot be cancelled.
* Order status is represented using `OrderStatus`.
* Order totals are automatically recalculated after adding or removing items.
* The program handles invalid IDs and empty collections without crashing.

---

## 📊 Example

### Menu

```text
1 - Burger - 150 - Main Course
2 - Pizza  - 200 - Main Course
3 - Pasta  - 180 - Main Course
4 - Cola   - 40  - Drinks
```

### Order #101

```text
Customer: Ahmed
Status: PENDING

Burger × 2 = 300
Cola   × 1 = 40

Total = 340
```

After adding the order to the kitchen:

```text
Status: IN_KITCHEN
```

After processing:

```text
Status: COMPLETED
```

The order remains in:

```java
orders
```

and is also added to:

```java
completedOrders
```

---

## 🔄 Order Lifecycle Diagram

```text
                    ┌───────────────┐
                    │    PENDING    │
                    └───────┬───────┘
                            │
                            │ Add to Kitchen
                            ▼
                    ┌───────────────┐
                    │  IN_KITCHEN   │
                    └───────┬───────┘
                            │
                            │ Process
                            ▼
                    ┌───────────────┐
                    │   COMPLETED   │
                    └───────────────┘


        PENDING ───────────────► CANCELLED
        
        IN_KITCHEN ────────────► CANCELLED
```

Throughout the lifecycle, the order remains inside:

```java
HashMap<Integer, Order> orders
```

---

## 🛠️ Technologies Used

* Java
* Java Collections Framework
* `ArrayList`
* `LinkedList`
* `HashMap`
* `LinkedHashMap`
* `Enum`
* Object-Oriented Programming
* Console Input/Output

---

## 💡 Key Concepts Demonstrated

This project demonstrates practical usage of:

* Collections
* Generics
* Custom Classes
* Encapsulation
* Enums
* ArrayList
* LinkedList
* HashMap
* LinkedHashMap
* Iteration using loops and `for-each`
* Searching
* Adding and removing elements
* Queue processing
* Object relationships
* State management

---

## 🚀 How to Run

1. Clone the repository.
2. Open the project in a Java-supported IDE.
3. Compile the Java source files.
4. Run the `Main` class.
5. Use the console menu to manage menu items and orders.

---

## 👨‍💻 Author

**Salah El-Din Yehia**

Computer Science Student

---

## 📌 Conclusion

The Restaurant Order Manager demonstrates how choosing the right Java Collection can make a system easier to design and manage.

Each collection has a specific responsibility:

```text
ArrayList     → Menu
LinkedList    → Kitchen Queue
HashMap       → All Orders
LinkedHashMap → Completed Orders
```

The project also demonstrates how an `enum` can control an order's lifecycle while keeping all orders stored permanently in the `HashMap`.
