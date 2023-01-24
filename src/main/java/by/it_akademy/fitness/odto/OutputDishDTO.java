package by.it_akademy.fitness.odto;

import by.it_akademy.fitness.storage.entity.CompositionDish;

import java.util.List;
import java.util.UUID;

public class OutputDishDTO {
    /*
    {
      "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "dt_create": 0,
      "dt_update": 0,
      "title": "Гречка с лисичками",
      "composition": [
        {
          "product": {
            "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "dt_create": 0,
            "dt_update": 0,
            "title": "Гречка",
            "weight": 100,
            "calories": 308,
            "proteins": 12.6,
            "fats": 3.3,
            "carbohydrates": 57.1
          },
          "weight": 100
        }
     */
    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private List<OutputComDishDTO> compositionDishes;
    private String createByRole;

    public OutputDishDTO() {
    }

    public OutputDishDTO(UUID id,
                         Long dtCreate,
                         Long dtUpdate,
                         String title,
                         List<OutputComDishDTO> compositionDishes,
                         String createByRole) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.compositionDishes = compositionDishes;
        this.createByRole = createByRole;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OutputComDishDTO> getCompositionDishes() {
        return compositionDishes;
    }

    public void setCompositionDishes(List<OutputComDishDTO> compositionDishes) {
        this.compositionDishes = compositionDishes;
    }

    public String getCreateByRole() {
        return createByRole;
    }

    public void setCreateByRole(String createByRole) {
        this.createByRole = createByRole;
    }
}