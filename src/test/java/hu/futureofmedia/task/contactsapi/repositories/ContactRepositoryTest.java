package hu.futureofmedia.task.contactsapi.repositories;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactTest;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown()
    {
        contactTest.deleteAll();
    }

    @Test
    void deleteByIdTest() {

        Company company = new Company(new Random().nextLong(),"Company #2");
        companyRepository.save(company);
        Contact contact = new Contact(new Random().nextLong(), "Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, company);

        contactTest.save(contact);

        assertThat(contactTest.findAll().size()).isEqualTo(1);

        List<Contact> contactList = contactTest.findAll();
        contactTest.deleteById(contactList.get(0).getId());

        assertThat(contactTest.findAll().size()).isEqualTo(0);

    }

    @Test
    void findContactByIdTest() {
        Company company = new Company(new Random().nextLong(),"Company #2");
        companyRepository.save(company);
        long randomId = new Random().nextLong();
        Contact contact = new Contact(randomId, "Kiss", "Aladar", "kiss@gmail.com", "+36301234567", "", Status.ACTIVE, company);
        contactTest.save(contact);

        assertThat(contactTest.findAll().size()).isEqualTo(1);

        Optional<Contact> contactFound = contactTest.findContactById(contactTest.findAll().get(0).getId());
        System.out.println(contactTest.findAll().get(0).getId());
        System.out.println(randomId);

        assertThat(contactFound.isEmpty()).isFalse();
    }
}