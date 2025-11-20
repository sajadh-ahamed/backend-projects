package lk.acpt.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.acpt.demo.dto.UserDto;
import lk.acpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JWtTokenGenerator {

    @Value("${demo.app.jwtSecret}")
    private String jwtSecret;

    @Value("${demo.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private final UserService userService;

    @Autowired
    public JWtTokenGenerator(UserService userService) {
        this.userService = userService;
    }

    public String generateJwtToken(UserDto user) {
        return Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());
        try {
            Jwts.parser().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT token");
        }
        return false;
    }

}
