package co.gestor.Inventario.seguridad.repository;

import co.gestor.Inventario.seguridad.entities.Attempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttemptsRepository extends JpaRepository<Attempts, Integer> {
   Optional<Attempts> findAttemptsByUsername(String username);
}