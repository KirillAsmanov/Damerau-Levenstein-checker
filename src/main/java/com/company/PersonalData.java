package com.company;

import java.util.Objects;

/**
 * Personal data model.
 */
public class PersonalData {
    private String firstName;
    private String lastName;
    private String patronymicName;


    public PersonalData(String firstName, String lastName, String patronymicName) {
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymicName(patronymicName);
    }

    /**
     * Checks string on null value or on max length mismatch.
     * @param someName input string.
     * @return valid string.
     */
    private String namesValidator(String someName) {
        if (someName == null) {
            throw new IllegalArgumentException ("Value must not be null");
        }

        final int MAX_NAMES_LENGTH = 50;

        if (someName.length() >= MAX_NAMES_LENGTH) {
            throw new IllegalArgumentException ("Length of names field must be less then " + MAX_NAMES_LENGTH);
        }
        return someName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = namesValidator(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = namesValidator(lastName);
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = namesValidator(patronymicName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalData that = (PersonalData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymicName, that.patronymicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymicName);
    }
}
