package org.example.ticketing.domain;

public class Address {
    private String street;
    private String city;

    public Address(String street, String city) {
        validateNotNull(street, city);
        this.street = street.trim();
        this.city = city.trim();
    }

    private void validateNotNull(String... fields) {
        for (String field : fields) {
            if (field == null)
                throw new IllegalArgumentException("Address fields must not be null");
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        validateNotNull(street);
        this.street = street.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        validateNotNull(city);
        this.city = city.trim();
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}
