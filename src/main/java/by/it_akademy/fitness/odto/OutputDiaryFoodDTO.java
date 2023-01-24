package by.it_akademy.fitness.odto;

import java.util.UUID;

public class OutputDiaryFoodDTO {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private Long dtSupply;
    private OutputDishDTO dish;
    private double weightDish;
    private OutputProductDTO product;
    private double weightProduct;
    private UUID profile;

    public OutputDiaryFoodDTO() {
    }

    public OutputDiaryFoodDTO(UUID id,
                              Long dtCreate,
                              Long dtUpdate,
                              Long dtSupply,
                              OutputDishDTO dish,
                              double weightDish,
                              OutputProductDTO product,
                              double weightProduct,
                              UUID profile) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.dtSupply = dtSupply;
        this.dish = dish;
        this.weightDish = weightDish;
        this.product = product;
        this.weightProduct = weightProduct;
        this.profile = profile;
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

    public Long getDtSupply() {
        return dtSupply;
    }

    public void setDtSupply(Long dtSupply) {
        this.dtSupply = dtSupply;
    }

    public OutputDishDTO getDish() {
        return dish;
    }

    public void setDish(OutputDishDTO dish) {
        this.dish = dish;
    }

    public double getWeightDish() {
        return weightDish;
    }

    public void setWeightDish(double weightDish) {
        this.weightDish = weightDish;
    }

    public OutputProductDTO getProduct() {
        return product;
    }

    public void setProduct(OutputProductDTO product) {
        this.product = product;
    }

    public double getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(double weightProduct) {
        this.weightProduct = weightProduct;
    }

    public UUID getProfile() {
        return profile;
    }

    public void setProfile(UUID profile) {
        this.profile = profile;
    }
}