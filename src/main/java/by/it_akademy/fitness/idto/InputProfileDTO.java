package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.util.EGender;
import by.it_akademy.fitness.util.ELifestyle;

import java.time.LocalDate;

public class InputProfileDTO {

    private double height;

    private double weight;

    private LocalDate birthday;

    private double targetWeight;

    private ELifestyle lifestyle;

    private EGender gender;

    public InputProfileDTO() {
    }

    public InputProfileDTO(double height,
                           double weight,
                           LocalDate birthday,
                           double targetWeight,
                           ELifestyle lifestyle,
                           EGender gender) {
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.targetWeight = targetWeight;
        this.lifestyle = lifestyle;
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public ELifestyle getLifestyle() {
        return lifestyle;
    }

    public EGender getGender() {
        return gender;
    }
}
