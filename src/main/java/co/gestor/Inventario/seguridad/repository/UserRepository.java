package co.gestor.Inventario.seguridad.repository;


import co.gestor.Inventario.seguridad.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
   Optional<User> findUserByUsername(String username);
}