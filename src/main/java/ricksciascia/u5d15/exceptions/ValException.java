package ricksciascia.u5d15.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValException extends RuntimeException {
    private List<String> listaErrori;
    public ValException(List<String> listaErrori) {
        super("Errori nel contenuto del payload, controlla la Lista degli errori");
        this.listaErrori = listaErrori;
    }
}
