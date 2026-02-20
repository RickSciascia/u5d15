package ricksciascia.u5d15.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(long id) {
        super("Il record con l'id " + id + " non è stato trovato.");
    }
}
