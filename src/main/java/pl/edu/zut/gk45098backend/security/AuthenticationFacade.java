package pl.edu.zut.gk45098backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pl.edu.zut.gk45098backend.model.User;

@Component
public class AuthenticationFacade {

    UserDetailsService userDetailsService;

    public AuthenticationFacade(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)userDetailsService.loadUserByUsername(authentication.getName());
        return user;
    }
}
