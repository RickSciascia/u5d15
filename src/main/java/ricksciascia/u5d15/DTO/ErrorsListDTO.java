package ricksciascia.u5d15.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO(String messaggio, LocalDateTime timestampErrore, List<String> listaErrori) {
}
