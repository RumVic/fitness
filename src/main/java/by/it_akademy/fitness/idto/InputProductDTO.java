package by.it_akademy.fitness.idto;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InputProductDTO {

    @NotNull
    private String title;
    @Min(value = 0, message ="No can have this value" )
    private double weight;
    @Min(value = 0, message ="No can have this value" )
    private double calories;
    @Min(value = 0, message ="No can have this value" )
    private double proteins;
    @Min(value = 0, message ="No can have this value" )
    private double fats;
    @Min(value = 0, message ="No can have this value" )
    private double carbohydrates;

  public InputProductDTO(String title,
                         double weight,
                         double calories,
                         double proteins,
                         double fats,
                         double carbohydrates) {
    this.title = title;
    this.weight = weight;
    this.calories = calories;
    this.proteins = proteins;
    this.fats = fats;
    this.carbohydrates = carbohydrates;
  }

    public String getTitle() {
        return title;
    }

    public double getWeight() {
        return weight;
    }

    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputProductDTO)) return false;
        InputProductDTO inputDTO = (InputProductDTO) o;
        return Double.compare(inputDTO.weight, weight) == 0
                && Double.compare(inputDTO.calories, calories) == 0
                && Double.compare(inputDTO.proteins, proteins) == 0
                && Double.compare(inputDTO.fats, fats) == 0
                && Double.compare(inputDTO.carbohydrates, carbohydrates) == 0
                && title.equals(inputDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "InputDTO{" +
                "title='" + title + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
