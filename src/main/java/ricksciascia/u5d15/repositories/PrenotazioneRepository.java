package ricksciascia.u5d15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ricksciascia.u5d15.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {
//    TODO: eventuali queries
    @Query("SELECT SUM (p.nPosti) FROM Prenotazione p WHERE p.evento.id = :idEvento")
    Integer sommaPostiEvento(long idEvento);
}
