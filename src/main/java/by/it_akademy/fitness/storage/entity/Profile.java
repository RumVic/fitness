package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.util.Gender;
import by.it_akademy.fitness.util.Lifestyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Profile {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    @ManyToOne
    private User user;
    private double height;
    private double weight;
    private LocalDate birthday;
    private Gender gender;
    private Lifestyle lifestyle;
    private double targetWeight;

    public Profile(UUID id,
                   Long dtCreate,
                   Long dtUpdate,
                   User user,
                   double height,
                   double weight,
                   LocalDate birthday,
                   Gender gender,
                   Lifestyle lifestyle,
                   double targetWeight) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.user = user;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.gender = gender;
        this.lifestyle = lifestyle;
        this.targetWeight = targetWeight;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
    }

    public Long getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

}
