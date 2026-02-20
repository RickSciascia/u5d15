package ricksciascia.u5d15.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ricksciascia.u5d15.entities.Utente;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Utente utente) {
        return Jwts.builder()
//                emissione nel momento attuale
                .issuedAt(new Date(System.currentTimeMillis()))
//                scadenza fra 3 giorni
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*3))
//                soggetto: quindi id utente
                .subject(String.valueOf(utente.getId()))
//                firma con segreto
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
