# 🛒 Person 2 — Order Processing System

> **Data Structure:** Queue + Stack  
> **Marks:** 15  
> **Files:** `src/order/Order.java`, `src/order/OrderQueue.java`, `src/order/UndoStack.java`

---

## 🎯 Your Goal

Build an order processing system where:
1. **Orders are processed in FIFO order** (first come, first served) → **Queue**
2. **Customers can undo the last item added** before confirming → **Stack**

---

## 📝 What You Need to Implement

### `Order.java` — Data model for an order
- Fields: `orderId`, `customerId`, `restaurantId`, `items` (list of item names), `totalPrice`, `status`
- Constructor + getters/setters
- `toString()` for display

### `OrderQueue.java` — Queue for order processing (FIFO)
- Implement using a **linked-list-based queue** (not `java.util.Queue`)
- `enqueue(Order order)` — add order to the back
- `dequeue()` — process the next order from the front
- `peek()` — view next order without removing
- `isEmpty()` — check if queue is empty
- `displayQueue()` — show all pending orders

### `UndoStack.java` — Stack for undo feature (LIFO)
- Implement using a **linked-list-based stack** (not `java.util.Stack`)
- `push(String item)` — add item to cart
- `pop()` — undo / remove last added item
- `peek()` — view the last added item
- `isEmpty()` — check if cart is empty
- `displayCart()` — show all items in current cart

---

## 💡 Why Queue + Stack?

Explain in your report:

**Queue (FIFO):**
- Orders must be processed in the order they arrive — this is exactly what a queue does
- Real-world analogy: customers lining up at a restaurant counter
- enqueue/dequeue are both O(1)

**Stack (LIFO):**
- "Undo last item" means removing the most recent addition — this is exactly what a stack does
- Real-world analogy: stacking plates, you remove from the top
- push/pop are both O(1)

---

## 🧪 How to Test Your Module

```java
public static void main(String[] args) {
    // Test the undo stack (building a cart)
    UndoStack cart = new UndoStack();
    cart.push("Nasi Lemak");
    cart.push("Teh Tarik");
    cart.push("Roti Canai");
    cart.displayCart();        // Shows all 3 items
    cart.pop();                // Undo → removes Roti Canai
    cart.displayCart();        // Shows 2 items

    // Test the order queue
    OrderQueue queue = new OrderQueue();
    queue.enqueue(new Order(1, 101, 201, ...));
    queue.enqueue(new Order(2, 102, 202, ...));
    queue.displayQueue();
    queue.dequeue();           // Process first order
    queue.displayQueue();      // First order gone
}
```

---

## 📊 Diagrams You Need for the Report

**Queue Diagram:**
```
FRONT → [Order 1] → [Order 2] → [Order 3] → REAR
         (next to       (waiting)    (just
          process)                    arrived)
```

**Stack Diagram:**
```
TOP → [Roti Canai]   ← last added, first to undo
      [Teh Tarik]
      [Nasi Lemak]   ← first added
```

---

## 🔗 How Your Module Connects to Others

- **Person 1** provides `User` / `Restaurant` data that your `Order` references
- **Person 3** (Delivery) picks up orders after they're dequeued and assigns a rider
- In `Main.java`, the flow is: build cart (Stack) → confirm order → enqueue (Queue) → dequeue for delivery
