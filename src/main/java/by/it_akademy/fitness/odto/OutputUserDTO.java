package by.it_akademy.fitness.odto;

import by.it_akademy.fitness.util.enams.EStatus;


import java.util.UUID;

public class OutputUserDTO {

    private UUID id;
    private Long dtCrate;
    private Long dtUpdate;
    private String login;
    private String role;
    private EStatus status;

    public OutputUserDTO() {
    }

    public OutputUserDTO(UUID id,
                         Long dtCrate,
                         Long dtUpdate,
                         String login,
                         String role,
                         EStatus status) {
        this.id = id;
        this.dtCrate = dtCrate;
        this.dtUpdate = dtUpdate;
        this.login = login;
        this.role = role;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getDtCrate() {
        return dtCrate;
    }

    public void setDtCrate(Long dtCrate) {
        this.dtCrate = dtCrate;
    }

    public Long getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
