package ricksciascia.u5d15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ricksciascia.u5d15.DTO.PrenotazioneDTO;
import ricksciascia.u5d15.entities.Prenotazione;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.services.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    @Autowired
    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated PrenotazioneDTO payload, @AuthenticationPrincipal Utente utenteLoggato) {
        return this.prenotazioneService.savePrenotazione(payload, utenteLoggato);
    }
}
