package ricksciascia.u5d15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricksciascia.u5d15.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
//    TODO: eventuali queries
}
