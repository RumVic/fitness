package by.it_akademy.fitness.idto;

import by.it_akademy.fitness.util.enams.EStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class InputUserByAdmin {

    @Email(regexp = ".+[@].+[\\.].+")
    private String mail;
    @NotNull
    private String nick;
    @NotNull
    private String password;
    @NotNull
    private String role;

    private EStatus status;

    public InputUserByAdmin() {
    }

    public InputUserByAdmin(String mail,
                            String nick,
                            String password,
                            String role,
                            EStatus status) {
        this.mail = mail;
        this.nick = nick;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
