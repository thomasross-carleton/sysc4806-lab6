package ca.carleton.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class AddressBookRestController {
    @Autowired
    private AddressBookRepository repository;

    @PostMapping("/api/addressbook")
    public AddressBook createAddressBook() {
        return this.repository.save(new AddressBook());
    }

    @GetMapping("/api/addressbook/{id}")
    public AddressBook getAddressBook(@PathVariable int id) {
        AddressBook addressBook = this.repository.findById(id);
        if (addressBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address book not found.");
        }

        return addressBook;
    }

    @PostMapping("/api/addressbook/{id}/buddy")
    public BuddyInfo addBuddyToAddressBook(@PathVariable int id, @RequestBody Map<String, String> buddyInfo) {
        AddressBook addressBook = this.repository.findById(id);
        if (addressBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address book not found.");
        }

        BuddyInfo buddy = new BuddyInfo(buddyInfo.get("name"), buddyInfo.get("address"), buddyInfo.get("phoneNumber"));
        addressBook.addBuddy(buddy);
        this.repository.save(addressBook);

        return buddy;
    }

    @DeleteMapping("/api/addressbook/{id}/buddy/{buddyId}")
    public AddressBook removeBuddyFromAddressBook(@PathVariable int id, @PathVariable int buddyId) {
        AddressBook addressBook = this.repository.findById(id);
        if (addressBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address book not found.");
        }

        addressBook.removeBuddy(buddyId);

        return this.repository.save(addressBook);
    }
}
