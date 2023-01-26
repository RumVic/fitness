package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.storage.entity.Dish;
import by.it_akademy.fitness.storage.entity.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class InputDiaryFoodDTO {

    private Long dtSupply;

    private Dish dish;

    private double weightDish;

    private Product product;

    private double weightProduct;

    private UUID profile;

    public InputDiaryFoodDTO(Long dtSupply,
                             Dish dish,
                             double weightDish,
                             Product product,
                             double weightProduct,
                             UUID  profile) {
        this.dtSupply = dtSupply;
        this.dish = dish;
        this.weightDish = weightDish;
        this.product = product;
        this.weightProduct = weightProduct;
        this.profile = profile;
    }

    public Long getDtSupply() {
        return dtSupply;
    }

    public Dish getDish() {
        return dish;
    }

    public double getWeightDish() {
        return weightDish;
    }

    public Product getProduct() {
        return product;
    }

    public double getWeightProduct() {
        return weightProduct;
    }

    public UUID getProfile() {
        return profile;
    }
}

