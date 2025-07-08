package org.example.ticketing.domain;

public class Location extends Address {
    private int capacity;

    public Location(String street, String city, int capacity) {
        super(street, city);
        validateCapacity(capacity);
        this.capacity = capacity;
    }

    private void validateCapacity(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be > 0");
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + " – " + capacity + " seats";
    }
}
