package pl.edu.zut.gk45098backend.projection.security;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Login {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String username;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
