package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.storage.entity.Profile;
import by.it_akademy.fitness.storage.entity.User;
import by.it_akademy.fitness.util.enams.EGender;
import by.it_akademy.fitness.util.enams.ELifestyle;
import java.time.LocalDate;
import java.util.UUID;

public class ProfileBuilder {

    private UUID id;

    private Long dtCreate;

    private Long dtUpdate;

    private User user;

    private double height;

    private double weight;

    private LocalDate birthday;

    private EGender gender;

    private  ELifestyle lifestyle ;

    private double targetWeight;

    private ProfileBuilder() {
    }

    public static ProfileBuilder create(){
        return new ProfileBuilder();
    }

    public ProfileBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ProfileBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public ProfileBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public ProfileBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public ProfileBuilder setHeight(double height) {
        this.height = height;
        return this;
    }

    public ProfileBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public ProfileBuilder setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public ProfileBuilder setGender(EGender gender) {
        this.gender = gender;
        return this;
    }

    public ProfileBuilder setLifestyle(ELifestyle lifestyle) {
        this.lifestyle = lifestyle;
        return this;
    }

    public ProfileBuilder setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
        return this;
    }

    public Profile build() {
        return new Profile(id,dtCreate,dtUpdate,user,height,weight,birthday,gender,lifestyle,targetWeight);
    }
}
