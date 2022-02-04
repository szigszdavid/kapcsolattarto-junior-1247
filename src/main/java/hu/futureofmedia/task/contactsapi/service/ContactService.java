package hu.futureofmedia.task.contactsapi.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.entities.ContactDTO;
import hu.futureofmedia.task.contactsapi.entities.Status;
import hu.futureofmedia.task.contactsapi.exception.UserNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.CompanyRepository;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, CompanyRepository companyRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public Contact addContact(ContactDTO contactDTO)
    {
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setStatus(contactDTO.getStatus());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setEmailAddress(contactDTO.getEmailAddress());
        contact.setComment(contactDTO.getComment());
        contact.setCompany(companyRepository.findCompanyByName(contactDTO.getCompany()));

        System.out.println(contactDTO.getCompany());
        System.out.println(companyRepository.findCompanyByName(contactDTO.getCompany()));

        String swissNumberStr = contact.getPhoneNumber();
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {

            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "HU");
            boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
            if(isValid)
            {
                contact.setPhoneNumber(phoneUtil.format(swissNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164));
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }

        return contactRepository.save(contact);
    }

    public List<Contact> findAllContacts()
    {
        return contactRepository.findAll();
    }

    public Contact updateContact(Contact contact, ContactDTO contactDTO)
    {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setStatus(contactDTO.getStatus());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setEmailAddress(contactDTO.getEmailAddress());
        contact.setComment(contactDTO.getComment());
        contact.setCompany(companyRepository.findCompanyByName(contactDTO.getCompany()));
        contact.setLastModifiedTime(new Date(System.currentTimeMillis()));
        String swissNumberStr = contact.getPhoneNumber();
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {

            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "HU");
            boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
            if(isValid)
            {
                contact.setPhoneNumber(phoneUtil.format(swissNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164));
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
        return contactRepository.save(contact);
    }

    public Contact findContactById(Long id)
    {
        return contactRepository.findContactById(id)
                .orElseThrow( () -> new UserNotFoundException("User by id" + id + "was not found"));
    }

    public void deleteContact(Long id)
    {
        contactRepository.deleteById(id);
    }

    public Contact deactivateContact(Contact contact)
    {
        contact.setStatus(Status.DELETED);
        return contactRepository.save(contact);
        //return contactRepository.deactivateContact(id);
    }


}
