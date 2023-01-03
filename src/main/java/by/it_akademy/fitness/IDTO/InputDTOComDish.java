package by.it_akademy.fitness.IDTO;

import by.it_akademy.fitness.storage.entity.Product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

public class InputDTOComDish {

    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private UUID dish; // reference to dish
    private List<Product> productList;
    private double weight;

    public InputDTOComDish() {
    }

    public InputDTOComDish(UUID id,
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

    public List<Product> getProductList() {
        return productList;
    }

    public double getWeight() {
        return weight;
    }

}
