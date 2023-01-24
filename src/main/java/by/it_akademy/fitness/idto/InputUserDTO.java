package by.it_akademy.fitness.idto;

import org.springframework.lang.NonNull;

public class InputUserDTO {
    @NonNull
    private String mail;
    @NonNull
    private String nick;
    private String password;

    public InputUserDTO() {
    }

    public InputUserDTO(String mail, String nick, String password) {
        this.mail = mail;
        this.nick = nick;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }
}
