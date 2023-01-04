package by.it_akademy.fitness.storage.entity;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
    private  List<CompositionDish> compositionDishes ;
    @Column(name = "create_by_role")
    private String createByRole;

    public Dish() {
    }

    public Dish(
            UUID id,
            Long dtCreate,
            Long dtUpdate,
            String title,
            List<CompositionDish> compositionDishList,
            String createByRole    ) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.compositionDishes = compositionDishList;
        this.createByRole = createByRole;
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

    public List<CompositionDish> getCompositionDishList() {
        return compositionDishes;
    }

    public void setCompositionDishList(List<CompositionDish> compositionDishes) {
        this.compositionDishes = compositionDishes;
    }
    public String getCreateByRole() {
        return createByRole;
    }
    public void setCreateByRole(String createByRole) {
        this.createByRole = createByRole;
    }
}
