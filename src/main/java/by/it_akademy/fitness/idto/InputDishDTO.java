package by.it_akademy.fitness.idto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InputDishDTO {
    @NotNull //javax validation constraint
    private String title;

    @NotEmpty
    private List<InputComDishDTO> comDishDTO;

    public InputDishDTO() {
    }

    public InputDishDTO(String title, List<InputComDishDTO> comDishDTO) {
        this.title = title;
        this.comDishDTO = comDishDTO;
    }

    public String getTitle() {
        return title;
    }

    public List<InputComDishDTO> getComDishDTO() {
        return comDishDTO;
    }


}
