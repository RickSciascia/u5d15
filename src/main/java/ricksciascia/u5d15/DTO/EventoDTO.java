package ricksciascia.u5d15.DTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventoDTO(
        @NotBlank(message = "il campo titolo è obbligatorio")
        @Size(min = 3,message = "Il titolo dell'evento deve avere almeno 3 caratteri")
        String titolo,
        @NotBlank(message = "il campo descrizione è obbligatorio")
        @Size(min = 5,message = "la descrizione dell'evento deve avere almeno 5 caratteri")
        String descrizione,
        @NotBlank(message = "il campo luogo è obbligatorio")
        @Size(min = 3,message = "Il luogo dell'evento deve avere almeno 3 caratteri")
        String luogo,
        @NotNull
        @FutureOrPresent(message = "La data dell'evento non può essere nel passato!")
        LocalDate data,
        @Min(value = 10, message = "il numero di posti deve essere minimo 10 per essere un evento!")
        int nPosti) {
}
