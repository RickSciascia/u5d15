package ricksciascia.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ricksciascia.u5d15.DTO.LoginDTO;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.UnauthorizedException;
import ricksciascia.u5d15.security.JWTTools;

@Service
public class AuthService {
    private final UtenteService utenteService;
    private final PasswordEncoder bcryptEncoder;
    private final JWTTools jwtTools;

    @Autowired
    public AuthService(UtenteService utenteService, PasswordEncoder bcryptEncoder, JWTTools jwtTools) {
        this.utenteService = utenteService;
        this.bcryptEncoder = bcryptEncoder;
        this.jwtTools = jwtTools;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO payload) {
//        recupero utente se esiste tramite metodo service findByEmail che gestisce già errore
        Utente trovato = this.utenteService.findByEmail(payload.email());
//        check password
        if(bcryptEncoder.matches(payload.password(), trovato.getPassword())) {
           return jwtTools.generateToken(trovato);
        } else throw new UnauthorizedException("Credenziali non valide, riprova!");
    }
}
