package ricksciascia.u5d15.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "eventi")
@NoArgsConstructor
@Getter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private String luogo;
    @Column(name = "n_posti", nullable = false)
    private int nPosti;
    @ManyToOne
    @JoinColumn(name = "id_organizzatore", nullable = false)
    private Utente organizzatore;

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int nPosti, Utente organizzatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.nPosti = nPosti;
        this.organizzatore = organizzatore;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", luogo='" + luogo + '\'' +
                ", nPosti=" + nPosti +
                ", organizzatore=" + organizzatore +
                '}';
    }
}
