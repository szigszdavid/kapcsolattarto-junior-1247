package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.ContactDTO;
import hu.futureofmedia.task.contactsapi.entities.Status;
import hu.futureofmedia.task.contactsapi.repositories.CompanyRepository;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private CompanyRepository companyRepository;
    private ContactService contactService;

    @BeforeEach
    void setUp()
    {
        contactService = new ContactService(contactRepository,companyRepository);
    }


    @Test
    void addContactTest() {

        Company company = new Company(new Random().nextLong(),"Company #2");
        companyRepository.save(company);
        Contact contact = new Contact(new Random().nextLong(), "Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, company);
        ContactDTO contactDTO = new ContactDTO("Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, "Company #2");

        contactService.addContact(contactDTO);

        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);

        verify(contactRepository).save(contactArgumentCaptor.capture());

        Contact  capturedContact = contactArgumentCaptor.getValue();

        System.out.println(capturedContact);
        System.out.println(contact);

        assertThat(capturedContact.getFirstName()).isEqualTo(contact.getFirstName());
        assertThat(capturedContact.getLastName()).isEqualTo(contact.getLastName());
        assertThat(capturedContact.getEmailAddress()).isEqualTo(contact.getEmailAddress());
        assertThat(capturedContact.getPhoneNumber()).isEqualTo(contact.getPhoneNumber());
    }

    @Test
    void findAllContactsTest() {
        contactService.findAllContacts();
        verify(contactRepository).findAll();
    }

    @Test
    void updateContactTest() {
        Company company = new Company(new Random().nextLong(),"Company #2");
        companyRepository.save(company);
        ContactDTO contactDTO = new ContactDTO("Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, "Company #2");
        ContactDTO contactDTONew = new ContactDTO("Nagy", "Mark", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, "Company #2");

        contactService.addContact(contactDTO);

        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);

        verify(contactRepository).save(contactArgumentCaptor.capture());

        Contact  capturedContact = contactArgumentCaptor.getValue();

        assertThat(capturedContact.getFirstName()).isEqualTo(contactDTO.getFirstName());
        assertThat(capturedContact.getLastName()).isEqualTo(contactDTO.getLastName());

        contactService.updateContact(capturedContact,contactDTONew);

        assertThat(capturedContact.getFirstName()).isEqualTo(contactDTONew.getFirstName());
        assertThat(capturedContact.getLastName()).isEqualTo(contactDTONew.getLastName());
    }


    @Test
    void deleteContactTest() {

        Company company = new Company(new Random().nextLong(),"Company #2");
        companyRepository.save(company);
        Contact contact = new Contact(new Random().nextLong(), "Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, company);
        ContactDTO contactDTO = new ContactDTO("Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, "Company #2");

        contactService.addContact(contactDTO);

        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);

        verify(contactRepository).save(contactArgumentCaptor.capture());

        Contact  capturedContact = contactArgumentCaptor.getValue();

        contactService.deleteContact(capturedContact.getId());

        assertThat(capturedContact.getId()).isNull();
    }

    @Test
    void deactivateContactTest() {
        Company company = new Company(new Random().nextLong(),"Company #2");
        companyRepository.save(company);
        Contact contact = new Contact(new Random().nextLong(), "Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, company);
        ContactDTO contactDTO = new ContactDTO("Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, "Company #2");

        contactService.addContact(contactDTO);

        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);

        verify(contactRepository).save(contactArgumentCaptor.capture());

        Contact  capturedContact = contactArgumentCaptor.getValue();

        contactService.deactivateContact(capturedContact);

        assertThat(capturedContact.getStatus()).isEqualTo(Status.DELETED);
    }
}