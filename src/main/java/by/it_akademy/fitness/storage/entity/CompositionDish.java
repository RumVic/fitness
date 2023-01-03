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

    private UUID dish; // reference to dish

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(insertable = false, updatable = false)
    private List<Product> productList;

    private double weight;

    public CompositionDish() {
    }

    public CompositionDish(
            UUID id,
            Long dtCreate,
            Long dtUpdate,
            String title,
            UUID dish,
            List<Product> productList,
            double weight) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.dish = dish;
        this.productList = productList;
        this.weight = weight;
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

    public UUID getDish() {
        return dish;
    }

    public void setDish(UUID dish) {
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
