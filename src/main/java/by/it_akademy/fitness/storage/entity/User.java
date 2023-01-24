package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.util.enams.EStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Data
@Table(name ="user_fitness")
public class User implements UserDetails {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCrate;
    @Column(name = "dt_update")
    private Long dtUpdate;

    private String username;
    @Column(unique = true)
    private String login;

    private String password;

    private String role;
    //private Collection<GrantedAuthority> role;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;

    public User() {
    }

    public User(UUID id,
                Long dtCrate,
                Long dtUpdate,
                String username,
                String login,
                String password,
                String role,
                EStatus status) {
        this.id = id;
        this.dtCrate = dtCrate;
        this.dtUpdate = dtUpdate;
        this.username = username;
        this.login = login;
        this.password = password;
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

    public String getUsername() {                                                       // TODO WARNING
        return login;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
