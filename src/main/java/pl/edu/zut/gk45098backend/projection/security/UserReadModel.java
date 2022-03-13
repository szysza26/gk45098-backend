package pl.edu.zut.gk45098backend.projection.security;

import pl.edu.zut.gk45098backend.model.User;

public class UserReadModel {
    private Long id;
    private String email;

    UserReadModel() { }

    public UserReadModel(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
