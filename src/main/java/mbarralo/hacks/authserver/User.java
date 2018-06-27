package mbarralo.hacks.authserver;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @ElementCollection
    @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USER"))
    @Column(name = "AUTHORITY")
    private List<String> authorities;

    public User() {
        authorities = new ArrayList<>();
    }

    public User(String user, String password, List<String> authorities) {

        this.username = user;
        this.password = password;
        this.enabled = true;

        List<String> authoritiesList = authorities.stream().map(s -> "ROLE_" + s).collect(Collectors.toList());
        this.authorities = authoritiesList;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
