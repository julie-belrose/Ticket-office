# 🎟️ Ticket Office

A minimalist Java console application for managing events and ticket reservations.

## 📌 Project
* We want to create a ticket management application for events.

* Our classes will be:

    * **Address**

        * Street
        * City

    * **Venue** (inherits from Address):

        * Capacity

    * **Ticket**:

        * Seat number
        * Client
        * Event
        * Seat type (standard, gold, VIP)

    * **Event**:

        * Name
        * Venue
        * Date
        * Time
        * Number of seats
        * List of tickets

    * **Client**:

        * Last name
        * First name
        * Address
        * Age
        * Phone number
        * List of tickets

---

### 1. Ticket Reservation:

* Clients will be able to book tickets for different events, and each ticket will be linked to a specific event.

### 2. Event Management:

* For each event, it will be possible to retrieve the list of associated tickets.

### 3. User Interface (UI):

* We want a user interface allowing CRUD operations (Create, Read, Update, Delete) for each entity.

### 4. Exception Handling:

* When searching for an element to display or add, if the specified index does not exist, we will throw a custom `NotFoundException`, which will be handled in our UI.

### 5. Bonus: Seat Availability Check:

* When booking a ticket for an event, we want to verify if there are still seats available.

---

## 🧱 Architecture

```
src/
├── Main.java                        # Entry point: bootstraps app and menu
│
├── domain/                          # Business entities and value objects
│   ├── Address.java
│   ├── Location.java (extends Address)
│   ├── Client.java
│   ├── Event.java
│   ├── Ticket.java
│   └── SeatType.java (enum)
│
├── domain/exception/               # Custom domain exceptions
│   ├── CrudException.java
│   ├── NotFoundException.java
│   └── CapacityFullException.java
│
├── repository/                     # In-memory repositories
│   ├── Repository.java (interface)
│   └── MapRepository.java
│
├── service/                        # Business services
│   └── TicketService.java
│
└── ui/                             # Console interaction logic
    ├── Ihm.java (generic CRUD menu)
    ├── MainMenu.java
    ├── ClientPrompter.java
    └── EventPrompter.java
```

---

## 🚀 Running the App

### ✅ Requirements

- Java 21+
- Maven

### ▶️ Launch

```bash
mvn clean compile exec:java
```

This will:

- Load demo data (1 client, 1 event, 1 reservation)
- Launch the interactive menu

---

## 🥪 Features

### 1. Ticket Reservation

- Supports different seat types: `STANDARD`, `GOLD`, `VIP`
- Prevents overbooking based on event capacity

### 2. CRUD Operations

- Manage clients and events via a reusable console interface
- Includes validation and exception handling

### 3. Exception Safety

- Custom exceptions such as `NotFoundException` and `CapacityFullException`
- All errors displayed gracefully to the user

### 4. UI Abstraction

- Generic `Ihm<T>` handles entity-specific menus
- Prompters (e.g. `ClientPrompter`) isolate prompting logic

---

## 📦 Sample Usage (preloaded)

```plaintext
=== TICKETING MENU ===
1 - Manage clients
2 - Manage events
3 - Reserve ticket
0 - Exit
```

1. Add or update a client (ID, name, address, age, phone)
2. Add an event with name, location, capacity
3. Reserve a ticket → ID check, seat number + type, capacity control
4. Visual confirmation of success or error message

---

## 🧠 Extending the App

- Add new entities: reuse `Ihm<T>` and a `Prompter`
- Swap `MapRepository` with SQL or file persistence
- Build REST or JavaFX layer on top (UI decoupled from logic)


