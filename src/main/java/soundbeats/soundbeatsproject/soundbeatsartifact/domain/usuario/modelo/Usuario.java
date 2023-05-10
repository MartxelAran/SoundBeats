package soundbeats.soundbeatsproject.soundbeatsartifact.domain.usuario.modelo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer userId;

    String nombre;

    String apellido;

    String numSS;
    
    Date fechaNacimiento;

    public Usuario(){}
}
