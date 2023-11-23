package co.gestor.Inventario.seguridad.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 

@Getter
@Entity
@Setter
public class Attempts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private int attempts;
    public void setId(int id) {
      this.id = id; 
   }
    public void setUsername(String username) {
      this.username = username; 
   }
    public void setAttempts(int attempts) {
      this.attempts = attempts; 
   } 
}