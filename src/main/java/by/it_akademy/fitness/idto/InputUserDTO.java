package by.it_akademy.fitness.idto;

public class InputUserDTO {

    private String mail;
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
