package by.it_akademy.fitness.storage.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product_fitness")
public class Product {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String title;

    private double weight;

    private double calories;

    private double proteins;

    private double fats;

    private double carbohydrates;
    @Column(name = "created_by_role")
    private String createdByRole; // who created product in db;

    public Product() {
    }

    public Product(
            UUID id,
            Long dtCreate,
            Long dtUpdate,
            String title,
            double weight,
            double calories,
            double proteins,
            double fats,
            double carbohydrates,
            String createdByRole) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.createdByRole = createdByRole;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getCreatedByRole() {
        return createdByRole;
    }

    public void setCreatedByRole(String createdByRole) {
        this.createdByRole = createdByRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getWeight(),
                getWeight()) == 0 && Double.compare(product.getCalories(),
                getCalories()) == 0 && Double.compare(product.getProteins(),
                getProteins()) == 0 && Double.compare(product.getFats(),
                getFats()) == 0 && Double.compare(product.getCarbohydrates(),
                getCarbohydrates()) == 0 && getId().equals(product.getId())
                && getDtCreate().equals(product.getDtCreate())
                && getDtUpdate().equals(product.getDtUpdate())
                && getTitle().equals(product.getTitle())
                && getCreatedByRole().equals(product.getCreatedByRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getDtCreate(),
                getDtUpdate(),
                getTitle(),
                getWeight(),
                getCalories(),
                getProteins(),
                getFats(),
                getCarbohydrates(),
                getCreatedByRole());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", createdByRole='" + createdByRole + '\'' +
                '}';
    }
}



