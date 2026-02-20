package ricksciascia.u5d15.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricksciascia.u5d15.DTO.EventoDTO;
import ricksciascia.u5d15.entities.Evento;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.NotFoundException;
import ricksciascia.u5d15.repositories.EventoRepository;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento saveEvento(EventoDTO payload, Utente organizzatore) {
        Evento eventoDaSalvare = new Evento(payload.titolo(), payload.descrizione(), payload.data(), payload.luogo(), payload.nPosti(), organizzatore );
        Evento salvato = this.eventoRepository.save(eventoDaSalvare);
        System.out.println("Evento: " +  salvato.getTitolo() + " salvato correttamente!");
        return salvato;
    }

    public Evento findEventoById(long idEvento) {
        return this.eventoRepository.findById(idEvento).orElseThrow(()-> new NotFoundException(idEvento));
    }

    public
}
