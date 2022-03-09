package pl.edu.zut.gk45098backend.controller.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zut.gk45098backend.model.User;
import pl.edu.zut.gk45098backend.projection.security.Login;
import pl.edu.zut.gk45098backend.projection.security.Token;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class LoginController {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.exp}")
    private Long exp;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/login")
    public ResponseEntity<Token> create(@Valid @RequestBody Login login) {
        User user = (User)userDetailsService.loadUserByUsername(login.getUsername());

        if(!passwordEncoder.matches(login.getPassword(), user.getPassword())){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + exp))
                .withClaim("role", user.getRole())
                .sign(Algorithm.HMAC256(secret));

        return new ResponseEntity<>(new Token(token), HttpStatus.OK);
    }

}
