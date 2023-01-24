package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.util.enams.EntityType;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "audit_fitness")
public class Audit {
    @Id
    private UUID id;

    @Column(name = "dt_create")
    private Long dtCreate;

    @Column(name = "dt_update")
    private Long dtUpdate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String text;

    @Enumerated(value = EnumType.STRING)
    private EntityType type;

    private String uid;

    public Audit() {
    }

    public Audit(UUID id,
                 Long dtCreate,
                 Long dtUpdate,
                 User user,
                 String text,
                 EntityType type,
                 String uid) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.uid = uid;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audit)) return false;
        Audit audit = (Audit) o;
        return getId().equals(audit.getId())
                && getDtCreate().equals(audit.getDtCreate())
                && getDtUpdate().equals(audit.getDtUpdate())
                && getUser().equals(audit.getUser())
                && getText().equals(audit.getText())
                && getType() == audit.getType()
                && getUid().equals(audit.getUid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getDtCreate(),
                getDtUpdate(),
                getUser(),
                getText(),
                getType(),
                getUid());
    }
}
