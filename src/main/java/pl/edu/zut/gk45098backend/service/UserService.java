package pl.edu.zut.gk45098backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.repository.UserRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication.getPrincipal() instanceof Jwt)) {
            throw new EntityNotFoundException();
        }
        Jwt principal = ((Jwt) authentication.getPrincipal());
        String email = principal.getClaimAsString("email");
        return userRepository.getUserByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

}
