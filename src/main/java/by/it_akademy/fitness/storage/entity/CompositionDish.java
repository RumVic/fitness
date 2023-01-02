package by.it_akademy.fitness.storage.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class CompositionDish {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String title;

    private Long dish; // reference to dish
    @ManyToMany
    private List<Product> productList;

    private double weight;

    public CompositionDish() {
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

    public Long getDish() {
        return dish;
    }

    public void setDish(Long dish) {
        this.dish = dish;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
