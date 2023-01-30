package by.it_akademy.fitness.idto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class InputUserDTO {
    @NotBlank
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
            message = "Email invalid")
    private String mail;
    @NotBlank
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
