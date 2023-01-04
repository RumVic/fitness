package by.it_akademy.fitness.IDTO;


import java.util.List;

public class InputDishDTO {

    private String title;

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
