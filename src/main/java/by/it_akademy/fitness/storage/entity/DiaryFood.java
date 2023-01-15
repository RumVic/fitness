package by.it_akademy.fitness.storage.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class DiaryFood {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    @Column(name = "dt_supply")
    private Long dtSupply;

    @ManyToOne(targetEntity = Dish.class )
    @JoinColumn
    private Dish dish;

    @Column(name = "weight_dish")
    private double weightDish;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @Column(name = "weight_product")
    private double weightProduct;

    private Long profile;

    public DiaryFood() {
    }

    public DiaryFood(UUID id,
                     Long dtCreate,
                     Long dtUpdate,
                     Long dtSupply,
                     Dish dish,
                     double weightDish,
                     Product product,
                     double weightProduct,
                     Long profile) {
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getWeightDish() {
        return weightDish;
    }

    public void setWeightDish(double weightDish) {
        this.weightDish = weightDish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(double weightProduct) {
        this.weightProduct = weightProduct;
    }

    public Long getProfile() {
        return profile;
    }

    public void setProfile(Long profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiaryFood)) return false;
        DiaryFood diaryFood = (DiaryFood) o;
        return Double.compare(diaryFood.getWeightDish(),
                getWeightDish()) == 0 && Double.compare(diaryFood.getWeightProduct(),
                getWeightProduct()) == 0 && getId().equals(diaryFood.getId())
                && getDtCreate().equals(diaryFood.getDtCreate())
                && getDtUpdate().equals(diaryFood.getDtUpdate())
                && getDtSupply().equals(diaryFood.getDtSupply())
                && getDish().equals(diaryFood.getDish())
                && getProduct().equals(diaryFood.getProduct())
                && getProfile().equals(diaryFood.getProfile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getDtCreate(),
                getDtUpdate(),
                getDtSupply(),
                getDish(),
                getWeightDish(),
                getProduct(),
                getWeightProduct(),
                getProfile());
    }
}
