package ca.carleton.addressbook;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BuddyInfo> buddies;

    public AddressBook() {
        this.id = null;
        this.buddies = new ArrayList<>();
    }

    /**
     * Get the address book's ID
     *
     * @return the address book's ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the address book's ID
     *
     * @param id the new ID of the address book
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Add a buddy to the buddies list
     *
     * @param buddy The buddy to add
     */
    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            this.buddies.add(buddy);
            buddy.setAddressBook(this);
        }
    }

    /**
     * Remove a buddy from the buddies list
     *
     * @param id The ID of the buddy to remove
     * @return The removed buddy, if applicable
     */
    public BuddyInfo removeBuddy(int id) {
        Iterator<BuddyInfo> iterator = this.buddies.iterator();
        while (iterator.hasNext()) {
            BuddyInfo buddyInfo = iterator.next();
            if (buddyInfo.getId() == id) {
                iterator.remove();
                buddyInfo.setAddressBook(null);

                return buddyInfo;
            }
        }

        return null;
    }

    /**
     * Get a buddy in the buddies list
     *
     * @param index The index of the buddy to get
     * @return The buddy
     */
    public BuddyInfo getBuddy(int index) {
        if (index >= 0 && index < this.buddies.size()) {
            return this.buddies.get(index);
        }

        return null;
    }

    /**
     * Get all buddies for this AddressBook.
     *
     * @return the buddies
     */
    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    /**
     * Return whether this object is equal to another.
     *
     * @param o The object to compare with
     * @return whether this object is equal to o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressBook that = (AddressBook) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.buddies, that.buddies);
    }

    @Override
    public String toString() {
        return "AddressBook " + this.id + ": " + buddies;
    }
}
