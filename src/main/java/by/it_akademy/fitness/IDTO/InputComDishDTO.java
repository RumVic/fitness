package by.it_akademy.fitness.IDTO;


import by.it_akademy.fitness.storage.entity.Product;

import java.util.UUID;

public class InputComDishDTO {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private UUID dish; // reference to dish
    private Product product;
    private double weight;

    public InputComDishDTO() {
    }

    public InputComDishDTO(UUID id,
                           Long dtCreate,
                           Long dtUpdate,
                           String title,
                           UUID dish,
                           Product product,
                           double weight) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.dish = dish;
        this.product = product;
        this.weight = weight;
    }

    public UUID getId() {
        return id;
    }

    public Long getDtCreate() {
        return dtCreate;
    }

    public Long getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public UUID getDish() {
        return dish;
    }

    public Product getProduct() {
        return product;
    }

    public double getWeight() {
        return weight;
    }

}
