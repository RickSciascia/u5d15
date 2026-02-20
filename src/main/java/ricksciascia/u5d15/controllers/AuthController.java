package ricksciascia.u5d15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ricksciascia.u5d15.DTO.LoginDTO;
import ricksciascia.u5d15.DTO.LoginResponseDTO;
import ricksciascia.u5d15.DTO.UtenteDTO;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.ValException;
import ricksciascia.u5d15.services.AuthService;
import ricksciascia.u5d15.services.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UtenteService utenteService;
    private final AuthService authService;

    @Autowired
    public AuthController(UtenteService utenteService, AuthService authService) {
        this.utenteService = utenteService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente registraDipendente(@RequestBody @Validated UtenteDTO payload, BindingResult validationRes) {
        if(validationRes.hasErrors()) {
            List<String> listaErrori = validationRes.getFieldErrors()
                    .stream().map(fe-> fe.getDefaultMessage())
                    .toList();
            throw new ValException(listaErrori);
        } else {
            return this.utenteService.saveUtente(payload);
        }
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO payload, BindingResult validationRes) {
        if(validationRes.hasErrors()) {
            List<String> listaErrori = validationRes.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValException(listaErrori);
        } else return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }
}
