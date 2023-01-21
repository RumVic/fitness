package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.util.EGender;
import by.it_akademy.fitness.util.ELifestyle;

import java.time.LocalDate;

public class InputProfileDTO {

    private double height;

    private double weight;

    private LocalDate dt_birthday;

    private double target;

    private ELifestyle activity_type;

    private EGender sex;

    public InputProfileDTO() {
    }

    public InputProfileDTO(double height,
                           double weight,
                           LocalDate dt_birthday,
                           double target,
                           ELifestyle activity_type,
                           EGender sex) {
        this.height = height;
        this.weight = weight;
        this.dt_birthday = dt_birthday;
        this.target = target;
        this.activity_type = activity_type;
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getBirthday() {
        return dt_birthday;
    }

    public double getTarget() {
        return target;
    }

    public ELifestyle getActivity_type() {
        return activity_type;
    }

    public EGender getSex() {
        return sex;
    }
}
