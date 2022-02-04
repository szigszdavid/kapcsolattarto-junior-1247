package hu.futureofmedia.task.contactsapi.entities;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @Column(name = "id")
    private Long id;

    public Company() {
    }

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
