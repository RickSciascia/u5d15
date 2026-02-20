package ricksciascia.u5d15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ricksciascia.u5d15.DTO.EventoDTO;
import ricksciascia.u5d15.entities.Evento;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.ValException;
import ricksciascia.u5d15.services.EventoService;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento creaEvento(@RequestBody @Validated EventoDTO payload, BindingResult validationRes, @AuthenticationPrincipal Utente organizzatore) {
        if(validationRes.hasErrors()) {
            List<String> listaErrori = validationRes.getFieldErrors()
                    .stream().map(fe-> fe.getDefaultMessage())
                    .toList();
            throw new ValException(listaErrori);
        } else {
            return this.eventoService.saveEvento(payload,organizzatore);
        }
    }
}
