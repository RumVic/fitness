package by.it_akademy.fitness.storage.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Dish {

    @Id
    private UUID id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    private String title;
    @Column(name = "composition_dish")
    private Long compositionDish;
    @OneToMany
    private List<CompositionDish> compositionDishList;

    public Dish() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCompositionDish() {
        return compositionDish;
    }

    public void setCompositionDish(Long compositionDish) {
        this.compositionDish = compositionDish;
    }

    public List<CompositionDish> getCompositionDishList() {
        return compositionDishList;
    }

    public void setCompositionDishList(List<CompositionDish> compositionDishList) {
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
                && getCompositionDish().equals(dish.getCompositionDish())
                && getCompositionDishList().equals(dish.getCompositionDishList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getDtCreate(),
                getDtUpdate(),
                getTitle(),
                getCompositionDish(),
                getCompositionDishList());
    }
}
