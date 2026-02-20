package ricksciascia.u5d15.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "prenotazioni")
@NoArgsConstructor
@Getter
@Setter
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(nullable = false)
    private int nPosti;
    @Column(nullable = false)
    private LocalDate dataPrenotazione;
    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    public Prenotazione(int nPosti,Utente utente, Evento evento) {
        this.nPosti = nPosti;
        this.dataPrenotazione = LocalDate.now();
        this.utente = utente;
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", nPosti=" + nPosti +
                ", dataPrenotazione=" + dataPrenotazione +
                ", utente=" + utente +
                ", evento=" + evento +
                '}';
    }
}
