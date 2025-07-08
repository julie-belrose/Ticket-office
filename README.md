## Ticket-office

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
