package ricksciascia.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ricksciascia.u5d15.DTO.UtenteDTO;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.BadReqException;
import ricksciascia.u5d15.exceptions.NotFoundException;
import ricksciascia.u5d15.repositories.UtenteRepository;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder bcryptEncoder) {
        this.utenteRepository = utenteRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public Utente saveUtente(UtenteDTO payload) {
        this.utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {throw new BadReqException("L'Email: " + utente.getEmail() + " è già registrata!");
        });
        Utente utenteDaSalvare = new Utente(payload.nome(), payload.cognome(), payload.email(), bcryptEncoder.encode(payload.password()),payload.ruolo());
        Utente salvato = this.utenteRepository.save(utenteDaSalvare);
        System.out.println("Utente: " + salvato.getNome() + " " + salvato.getCognome() + " con email: " + salvato.getEmail() +" con ruolo " + salvato.getRuolo() + " registrato correttamente!");
        return salvato;
    }

    public Utente findUtenteById(long idUtente) {
        return this.utenteRepository.findById(idUtente).orElseThrow(()-> new NotFoundException(idUtente));
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("Utente con email " + email + " non trovato!"));
    }
}
