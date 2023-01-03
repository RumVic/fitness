package by.it_akademy.fitness.IDTO;


import java.util.Objects;

public class InputDTODish {

    private String title;

    private Long compositionDish;

    private InputDTOComDish compositionDishObject;

    public InputDTODish() {
    }

    public InputDTODish(String title, Long compositionDish, InputDTOComDish compositionDishObject) {
        this.title = title;
        this.compositionDish = compositionDish;
        this.compositionDishObject = compositionDishObject;
    }

    public String getTitle() {
        return title;
    }

    public Long getCompositionDish() {
        return compositionDish;
    }

    public InputDTOComDish getCompositionDishObject() {
        return compositionDishObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputDTODish)) return false;
        InputDTODish that = (InputDTODish) o;
        return getTitle().equals(that.getTitle())
                && getCompositionDish().equals(that.getCompositionDish())
                && getCompositionDishObject().equals(that.getCompositionDishObject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getTitle(),
                getCompositionDish(),
                getCompositionDishObject());
    }

    @Override
    public String toString() {
        return "InputDTODish{" +
                "title='" + title + '\'' +
                ", compositionDish=" + compositionDish +
                ", compositionDishList=" + compositionDishObject +
                '}';
    }
}
