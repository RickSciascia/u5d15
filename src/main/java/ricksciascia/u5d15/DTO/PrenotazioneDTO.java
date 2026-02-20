package ricksciascia.u5d15.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO(
        @NotNull(message = "L'Id evento obbligatorio per la prenotazione!")
        long idEvento,
        @Min(value = 1, message = "La prenotazione deve essere per almeno 1 persona")
        int nPosti
) {
}
