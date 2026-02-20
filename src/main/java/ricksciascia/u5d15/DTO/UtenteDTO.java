package ricksciascia.u5d15.DTO;

import jakarta.validation.constraints.*;
import ricksciascia.u5d15.entities.RuoloUtente;

public record UtenteDTO(
        @NotBlank(message = "Il campo nome è obbligatorio")
        @Size(min = 2,message = "il nome deve essere minimo 2 caratteri")
        String nome,
        @NotBlank(message = "Il campo cognome è obbligatorio")
        @Size(min = 2,message = "il cognome deve essere minimo 2 caratteri")
        String cognome,
        @NotBlank(message = "il campo email è obbligatorio")
        @Email(message = "Formato non corretto controlla la presenza di @ nella tua email!")
        String email,
        @NotBlank(message = "il campo password è obbligatorio")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "La password deve contenere almeno una maiuscola, minuscola, numero ed essere minimo di 8 caratteri")
        String password,
        @NotNull(message = "il ruolo è obbligatorio")
        RuoloUtente ruolo
) {
}
