package Projetct.reservas.Repositories;

import Projetct.reservas.Entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long > {
    List<Reserva> findBySalaId(Long salaId);
}
