package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.serializator.DateDeSerializer;
import by.it_akademy.fitness.util.enams.EGender;
import by.it_akademy.fitness.util.enams.ELifestyle;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Date;

public class InputProfileDTO {
    private double height;

    private double weight;

    @JsonDeserialize(using = DateDeSerializer.class)
    private Date dt_birthday;

    private double target;

    private ELifestyle activity_type;

    private EGender sex;

    public InputProfileDTO() {
    }

    public InputProfileDTO(double height,
                           double weight,
                           Date dt_birthday,
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

    public Date getBirthday() {
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
