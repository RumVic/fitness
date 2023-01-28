package by.it_akademy.fitness.odto;

import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EGender;
import by.it_akademy.fitness.util.enams.ELifestyle;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class OutputProfileDTO {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private double height;
    private double weight;
    private Date birthday;
    private EGender gender;
    private ELifestyle lifestyle;
    private double targetWeight;
    private OutputUserDTO user;

    public OutputProfileDTO() {
    }

    public OutputProfileDTO(UUID id,
                            Long dtCreate,
                            Long dtUpdate,
                            double height,
                            double weight,
                            Date birthday,
                            EGender gender,
                            ELifestyle lifestyle,
                            double targetWeight,
                            OutputUserDTO user) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.gender = gender;
        this.lifestyle = lifestyle;
        this.targetWeight = targetWeight;
        this.user = user;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public ELifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(ELifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public OutputUserDTO getUser() {
        return user;
    }

    public void setUser(OutputUserDTO user) {
        this.user = user;
    }
}
