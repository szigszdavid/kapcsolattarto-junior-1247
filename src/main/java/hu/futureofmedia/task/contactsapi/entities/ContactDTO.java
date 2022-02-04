package hu.futureofmedia.task.contactsapi.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class ContactDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "First name should be added")
    private String firstname;

    @Column(nullable = false)
    @NotEmpty(message = "Last name should be added")
    private String lastname;

    @Column(nullable = false)
    @Email(message = "It should be e-mail address format")
    @NotEmpty(message = "E-mail should be added")
    private String email_address;

    @Column
    private String phone_number;


    @Column
    private String company;

    @Column(nullable = true)
    private String comment;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status should be added")
    private Status mystatus;

    public ContactDTO() {
    }

    public ContactDTO(String firstname, String lastname, String email_address, String phone_number, String comment, Status mystatus, String company) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.comment = comment;
        this.mystatus = mystatus;
        this.company = company;

    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Status getStatus() {
        return mystatus;
    }

    public void setStatus(Status mystatus) {
        this.mystatus = mystatus;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                ", firstName=" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", emailAddress='" + email_address + '\'' +
                ", phoneNumber=" + phone_number +
                ", comment='" + comment + '\'' +
                ", status='" + mystatus + '\'' +
                ", company=" + company +
                '}';
    }
}
