package ca.carleton.addressbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressBookController.class)
class AddressBookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookRepository repository;

    @Test
    void showsAddressBooks() throws Exception {
        AddressBook addressBook1 = new AddressBook();
        addressBook1.setId(1);
        AddressBook addressBook2 = new AddressBook();
        addressBook2.setId(2);
        when(this.repository.findAll()).thenReturn(Arrays.asList(addressBook1, addressBook2));

        this.mockMvc.perform(get("/addressbook"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Address Book #1")))
                .andExpect(content().string(containsString("Address Book #2")));
    }
}
