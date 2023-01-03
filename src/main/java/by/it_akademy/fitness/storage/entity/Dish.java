package by.it_akademy.fitness.storage.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Dish {

    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCreate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private CompositionDish compositionDishList;

    public Dish() {
    }

    public Dish(
            UUID id,
            Long dtCreate,
            Long dtUpdate,
            String title,
            CompositionDish compositionDishList) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.compositionDishList = compositionDishList;
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

    public CompositionDish getCompositionDishList() {
        return compositionDishList;
    }

    public void setCompositionDishList(CompositionDish compositionDishList) {
        this.compositionDishList = compositionDishList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return getId().equals(dish.getId())
                && getDtCreate().equals(dish.getDtCreate())
                && getDtUpdate().equals(dish.getDtUpdate())
                && getTitle().equals(dish.getTitle())
                && getCompositionDishList().equals(dish.getCompositionDishList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getDtCreate(),
                getDtUpdate(),
                getTitle(),
                getCompositionDishList());
    }
}
