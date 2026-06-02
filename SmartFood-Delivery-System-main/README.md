# 🍔 SmartFood Delivery & Order Management System

> **Course Project** — Data Structures & Algorithms  
> **Language:** Java  
> **Team Size:** 5 members

---

## 📋 Project Overview

GoodTech needs a system to manage users, restaurants, orders, and delivery services.  
Each module uses a **specific data structure** — your job is to implement it, explain *why* it's efficient, and integrate it with the rest of the system.

---

## 👥 Team Task Assignments

| Person | Module | Data Structure | Files to Work On | Marks |
|--------|--------|---------------|-------------------|-------|
| **Person 1** | User & Restaurant Management | Array / Linked List | `src/user/` | 15 |
| **Person 2** | Order Processing System | Queue + Stack | `src/order/` | 15 |
| **Person 3** | Delivery Rider Assignment | Priority Queue (Min Heap) | `src/delivery/` | 15 |
| **Person 4** | Route Optimization | Graph + Dijkstra's Algorithm | `src/route/` | 20 |
| **Person 5** | Search, Recommendation & Data Retrieval | BST + HashMap | `src/search/` | 25 |

> **Shared (everyone):** System Integration (`src/main/Main.java`) = 5 marks  
> **Shared (everyone):** Presentation & Report with Diagrams = 5 marks

---

## 📁 Project Structure

```
SmartFood-Delivery-System-main/
│
├── README.md                  ← You are here
├── docs/
│   ├── PERSON_1_GUIDE.md      ← Detailed guide for Person 1
│   ├── PERSON_2_GUIDE.md      ← Detailed guide for Person 2
│   ├── PERSON_3_GUIDE.md      ← Detailed guide for Person 3
│   ├── PERSON_4_GUIDE.md      ← Detailed guide for Person 4
│   └── PERSON_5_GUIDE.md      ← Detailed guide for Person 5
│
├── src/
│   ├── user/                  ← Person 1's module
│   │   ├── User.java
│   │   ├── UserList.java
│   │   ├── Restaurant.java
│   │   └── RestaurantList.java
│   │
│   ├── order/                 ← Person 2's module
│   │   ├── Order.java
│   │   ├── OrderQueue.java
│   │   └── UndoStack.java
│   │
│   ├── delivery/              ← Person 3's module
│   │   ├── Rider.java
│   │   ├── MinHeap.java
│   │   └── DeliveryAssignment.java
│   │
│   ├── route/                 ← Person 4's module
│   │   ├── Location.java
│   │   ├── Graph.java
│   │   └── Dijkstra.java
│   │
│   ├── search/                ← Person 5's module
│   │   ├── FoodItem.java
│   │   ├── FoodBST.java
│   │   └── DataHashMap.java
│   │
│   └── main/
│       └── Main.java          ← Everyone integrates here
│
└── diagrams/                  ← Put your report diagrams here
```

---

## 🚀 How to Run

```bash
# Compile all files
javac src/user/*.java src/order/*.java src/delivery/*.java src/route/*.java src/search/*.java src/main/Main.java

# Run the system
java -cp src main.Main
```

---

## 🔗 How the Modules Connect

```
Customer/Restaurant Data (Person 1)
        │
        ▼
  Order Placed (Person 2)  ──→  Undo last item (Stack)
        │
        ▼
  Assign Best Rider (Person 3)
        │
        ▼
  Find Shortest Route (Person 4)
        │
        ▼
  Search Food / Retrieve Data (Person 5)
```

**Integration Flow in `Main.java`:**
1. Person 1's module provides user & restaurant data
2. Person 2's module handles the order queue + undo
3. Person 3's module picks the best rider from a min-heap
4. Person 4's module finds the shortest delivery route
5. Person 5's module lets users search food + fast data retrieval

---

## ✅ Checklist Before Submission

- [ ] Each module compiles and runs independently
- [ ] `Main.java` calls all modules and demonstrates the full flow
- [ ] Each person wrote an explanation of **why** their data structure is efficient (include Big-O)
- [ ] Diagrams are saved in `diagrams/` folder
- [ ] Code is commented with your name at the top of each file you wrote
- [ ] Report includes: Stack, Queue, Graph, Tree, and Heap diagrams

---

## 📌 Rules for the Team

1. **Only edit files in YOUR module folder** — don't touch others' code
2. **Put your name** at the top of every file: `// Author: [Your Name]`
3. **Test your module** with a small `main()` inside your own class before integrating
4. **Read your guide** in `docs/PERSON_X_GUIDE.md` — it has everything you need
5. **Commit often** with clear messages like `"Person 2: implemented OrderQueue"`

---

## 📖 Marking Rubrics (Quick Reference)

| Component | DS Technique | Marks |
|-----------|-------------|-------|
| User & Restaurant Management | Array / Linked List | 15 |
| Order Processing System | Queue + Stack | 15 |
| Delivery Assignment | Priority Queue (Min Heap) | 15 |
| Route Optimization | Graph + Dijkstra | 20 |
| Search & Recommendation | BST / AVL Tree | 15 |
| Data Retrieval Optimization | Hash Table (HashMap) | 10 |
| System Integration | All combined | 5 |
| Presentation & Report | Diagrams | 5 |
| **TOTAL** | | **100** |
