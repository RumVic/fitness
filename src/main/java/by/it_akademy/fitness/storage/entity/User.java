package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.util.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCrate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String name;

    private String login;

    private String password;

    private String role;

    private Status status;

}
