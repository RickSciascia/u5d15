package ricksciascia.u5d15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricksciascia.u5d15.entities.Utente;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {
//    derived query per verifica email
    Optional<Utente> findByEmail(String email);
}
