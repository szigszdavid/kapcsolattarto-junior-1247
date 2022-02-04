package hu.futureofmedia.task.contactsapi;

import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.ContactDTO;
import hu.futureofmedia.task.contactsapi.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllContact()
    {
        List<Contact> contacts = contactService.findAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id)
    {
        Contact contact = contactService.findContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> addContact(@RequestBody ContactDTO contactDTO)
    {
        System.out.println("Addolás");
        Contact newContact = contactService.addContact(contactDTO);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id,@RequestBody ContactDTO contactDTO)
    {
        Contact contact = contactService.findContactById(id);
        Contact updateContact = contactService.updateContact(contact, contactDTO);
        return new ResponseEntity<>(updateContact, HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Contact> deactivateContact(@PathVariable("id") Long id)
    {
        Contact contact = contactService.findContactById(id);
        Contact deactivateContact = contactService.deactivateContact(contact);
        return new ResponseEntity<>(deactivateContact, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateContact(@PathVariable("id") Long id)
    {
        System.out.println("IDIM: " + id);
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
