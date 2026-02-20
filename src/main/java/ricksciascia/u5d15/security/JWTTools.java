package ricksciascia.u5d15.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.UnauthorizedException;

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

    public void verifyToken(String token) {
//        tutto dentro un try-catch perchè questo metodo potrebbe lanciare delle eccezioni che voglio poi gestire
        try {
//            parser estrae
            Jwts.parser()
//                    verifico con lo stessa chiave che ho usato per generare la firma
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch(Exception ex) {
            throw new UnauthorizedException("Problemi con il token, effettua nuovamente il login per favore!");
        }
    }

    public long extractIdFromToken(String token) {
//        leggo token con .parser
        return Long.parseLong(
                Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
//                        estraggo i claims > payload e subject che sarebbe id messo prima quando ho creato token
                        .parseSignedClaims(token).getPayload().getSubject()
        );
    }
}
