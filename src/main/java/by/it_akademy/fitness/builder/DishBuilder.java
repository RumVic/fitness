package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.IDTO.InputDTOComDish;
import by.it_akademy.fitness.storage.entity.CompositionDish;
import by.it_akademy.fitness.storage.entity.Dish;

import java.util.UUID;

public class DishBuilder {
    
    private UUID id;
    private Long dtCreate;
    private Long dtUpdate;
    private String title;
    private CompositionDish compositionDish;

    private DishBuilder() {
    }
    
    public static DishBuilder create(){
        return new DishBuilder();
    }

    public DishBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public DishBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public DishBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public DishBuilder setTitle(String title) {
        this.title = title;
        return this;
    }


    public DishBuilder setCompositionDish(CompositionDish compositionDish) {
        this.compositionDish = compositionDish;
        return this;
    }

    public Dish build(){
        return new Dish(id,dtCreate,dtUpdate,title, compositionDish);
    }
}
