package higold.by.flatrent.utils;

import higold.by.flatrent.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    private final String secret = "kjjkbh3354gdc1";
    private final Duration jwtLifeTime = Duration.ofMinutes(240);

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getName());

        Date issuedDate = new Date();
        Date expiredDate  = new Date(issuedDate.getTime() +  jwtLifeTime.toMillis());

        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(user.getEmail())
                   .setIssuedAt(issuedDate)
                   .setExpiration(expiredDate)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
    }

}

