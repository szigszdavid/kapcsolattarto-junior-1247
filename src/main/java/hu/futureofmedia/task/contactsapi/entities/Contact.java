package hu.futureofmedia.task.contactsapi.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, name = "firstname")
    @NotEmpty(message = "First name should be added")
    private String firstname;

    @Column(nullable = false, name = "lastname")
    @NotEmpty(message = "Last name should be added")
    private String lastname;

    @Column(nullable = false, name = "email_address")
    @Email(message = "It should be e-mail address format")
    @NotEmpty(message = "E-mail should be added")
    private String email_address;

    @Column(name = "phone_number")
    private String phone_number;

    @OneToOne
    @JoinColumn(name = "company_id")
    @NotNull
    private Company company;

    @Column(nullable = true,name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status should be added")
    private Status mystatus;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "created_time")
    private Date createdTime = new Date(System.currentTimeMillis());

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "last_modified_time")
    private Date lastModifiedTime = new Date(System.currentTimeMillis());

    public Contact() {
    }

    public Contact(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Contact(Long id, String firstname, String lastname, String email_address, String phone_number, String comment, Status mystatus, Company company) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.comment = comment;
        this.mystatus = mystatus;
        this.company = company;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
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
        return "Contact{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email_address='" + email_address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", company=" + company +
                ", comment='" + comment + '\'' +
                ", mystatus=" + mystatus +
                ", createdTime=" + createdTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
