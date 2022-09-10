package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.entities.RegisteredUser;
import com.dropit.backend_drop_it.repositories.RegisteredUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private final static String SECRET_KEY = "cakeBuglesFruityDuos";
    private final RegisteredUserRepository registeredUserRepository;

    public JwtServiceImpl(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before((new Date()));
    }

    @Override
    public String generateToken(UserDetails userDetails) {

        RegisteredUser user = registeredUserRepository.getReferenceById(userDetails.getUsername());

        return createToken(userDetails.getUsername(), userDetails.getAuthorities().toString(), user.getEmail());
    }

    private String createToken(String username, String authorities, String email) {
        authorities = authorities.replaceAll("[\\[\\]]", "");
        String[] claims = authorities.split(",");

        long validPeriod = 1000 * 60 * 60 * 24; // 1 day

        return Jwts.builder()
                .setSubject(username)
                .setIssuer(email)
                .claim("Authorities", claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + validPeriod))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
