package ca.carleton.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class AddressBookController {
    @Autowired
    private AddressBookRepository repository;

    @GetMapping("/addressbook")
    public String displayAddressBooks(Model model) {
        Iterable<AddressBook> addressBooks = this.repository.findAll();

        model.addAttribute("addressBooks", addressBooks);;
        model.addAttribute("buddy", new BuddyInfo());

        return "addressbook";
    }

    @PostMapping("/addressbook")
    public String createAddressBook(Model model, @ModelAttribute AddressBook addressBook) {
        this.repository.save(addressBook);

        return "redirect:/addressbook";
    }

    @PostMapping("/addressbook/{addressBookId}/buddy")
    public String addBuddyToAddressBook(Model model, @PathVariable int addressBookId, @ModelAttribute BuddyInfo buddy) {
        AddressBook addressBook = this.repository.findById(addressBookId);
        if (addressBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address book not found.");
        }

        addressBook.addBuddy(buddy);
        this.repository.save(addressBook);

        return "redirect:/addressbook";
    }
}
