package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Product;

import java.util.List;
import java.util.UUID;

public class CompositionDishBuilder {
    
    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private UUID dish; // reference to dish
    private List<Product> productList;
    private double weight;

    private CompositionDishBuilder() {
    }
    
    public static CompositionDishBuilder create(){
        return new CompositionDishBuilder();
    }

    public CompositionDishBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public CompositionDishBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public CompositionDishBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public CompositionDishBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CompositionDishBuilder setDish(UUID dish) {
        this.dish = dish;
        return this;
    }

    public CompositionDishBuilder setProductList(List<Product> productList) {
        this.productList = productList;
        return this;
    }

    public CompositionDishBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public CompositionDish build(){
        return new CompositionDish(id,dtCreate,dtUpdate,title,dish,productList,weight);
    }
}
