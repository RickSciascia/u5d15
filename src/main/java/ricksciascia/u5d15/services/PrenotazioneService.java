package ricksciascia.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricksciascia.u5d15.DTO.PrenotazioneDTO;
import ricksciascia.u5d15.entities.Evento;
import ricksciascia.u5d15.entities.Prenotazione;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final EventoService eventoService;

    @Autowired
    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, EventoService eventoService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.eventoService = eventoService;
    }

    public Prenotazione savePrenotazione(PrenotazioneDTO payload, Utente utente) {
        Evento eventoDaPrenotare = this.eventoService.findEventoById(payload.idEvento());
        
        Prenotazione prenotazioneDaSalvare = new Prenotazione(payload.nPosti(), utente, eventoDaPrenotare);
        Prenotazione salvata = this.prenotazioneRepository.save(prenotazioneDaSalvare);
        System.out.println("Prenotazione dell utente: " + utente.getNome() + " " + utente.getCognome() + " per evento: " + eventoDaPrenotare.getTitolo() + " avvenuta correttamente!");
        return salvata;
    }
}
