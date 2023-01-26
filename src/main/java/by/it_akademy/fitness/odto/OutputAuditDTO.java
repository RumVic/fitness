package by.it_akademy.fitness.odto;

import by.it_akademy.fitness.util.enams.EntityType;


public class OutputAuditDTO {

    private OutputUserDTO user;

    private String text;

    private EntityType type;

    private String uid;

    public OutputAuditDTO() {
    }

    public OutputAuditDTO(OutputUserDTO user, String text, EntityType type, String uid) {
        this.user = user;
        this.text = text;
        this.type = type;
        this.uid = uid;
    }

    public OutputUserDTO getUser() {
        return user;
    }

    public void setUser(OutputUserDTO user) {
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
}
