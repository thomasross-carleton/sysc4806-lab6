package ca.carleton.addressbook;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String phoneNumber;

    @ManyToOne
    @JsonBackReference
    private AddressBook addressBook;

    /**
     * Create a BuddyInfo.
     */
    public BuddyInfo() {
        this.id = null;
        this.name = null;
        this.address = null;
        this.phoneNumber = null;
    }

    /**
     * Create a BuddyInfo.
     *
     * @param name        The buddy's name
     * @param address     The buddy's address
     * @param phoneNumber The buddy's phone number
     */
    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the buddy's ID.
     *
     * @return the buddy's ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the buddy's ID.
     *
     * @param id the buddy's new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the buddy's name.
     *
     * @return the buddy's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the buddy's name
     *
     * @param name the buddy's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the buddy's address.
     *
     * @return the buddy's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Set the buddy's address.
     *
     * @param address the buddy's new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the buddy's phone number.
     *
     * @return the buddy's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Set the buddy's phone number.
     *
     * @param phoneNumber the buddy's new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the address book this buddy belongs to
     *
     * @return the address book
     */
    public AddressBook getAddressBook() {
        return addressBook;
    }

    /**
     * Set the address book this buddy belongs to
     *
     * @param addressBook the new address book
     */
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Return whether this object is equal to another.
     *
     * @param o The object to compare with
     * @return whether this object is equal to o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuddyInfo buddyInfo = (BuddyInfo) o;
        return Objects.equals(this.id, buddyInfo.id) &&
                Objects.equals(this.name, buddyInfo.name) &&
                Objects.equals(this.address, buddyInfo.address) &&
                Objects.equals(this.phoneNumber, buddyInfo.phoneNumber) &&
                Objects.equals(this.addressBook, buddyInfo.addressBook);
    }

    /**
     * Return a string representation of the buddy
     *
     * @return a string representation of the buddy
     */
    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.address + "," + this.phoneNumber;
    }
}
