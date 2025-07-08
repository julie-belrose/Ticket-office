package org.example.ticketing.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Client {
    private static final String PHONE_REGEX = "\\+?\\d{10,15}";
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private int age;
    private String phoneNumber;
    private final List<Ticket> tickets;

    public Client(String id, String firstName, String lastName, Address address, int age, String phoneNumber) {

        validateAge(age);
        validateNotNull(id, "id");
        validateNotNull(firstName, "firstName");
        validateNotNull(lastName, "lastName");
        validateNotNull(address, "address");
        validatePhone(phoneNumber);
        this.id         = id;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.address    = address;
        this.age        = age;
        this.phoneNumber = phoneNumber;
        this.tickets    = new ArrayList<>();
    }

    private void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be >= 0");
        }
    }

    private void validateNotNull(Object obj, String field) {
        if (obj == null) throw new IllegalArgumentException(field + " must not be null");
    }

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw new IllegalArgumentException("Phone number is invalid");
        }
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be >= 0");
        }
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhone(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicketById(String ticketId) {
        tickets.removeIf(ticket -> ticket.getId().equals(ticketId));
    }

    @Override
    public String toString() {
        return "[" + id + "] " + firstName + " " + lastName + " – " + age + " yrs – " + phoneNumber + " – " + address;
    }
}
