package by.it_akademy.fitness.storage.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="composition_dish_fitness")
public class CompositionDish {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String title;

    private UUID dish; // reference to dish

    @OneToOne(fetch = FetchType.EAGER)//cascade = CascadeType.ALL,
    //@JoinColumn(insertable = false, updatable = false)
    private Product product;

    private double weight;

    public CompositionDish() {
    }

    public CompositionDish(
            UUID id,
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

    public Product getProductList() {
        return product;
    }

    public void setProductList(Product product) {
        this.product = product;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
