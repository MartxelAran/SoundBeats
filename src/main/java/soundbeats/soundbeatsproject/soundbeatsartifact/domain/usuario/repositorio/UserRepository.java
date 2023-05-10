package soundbeats.soundbeatsproject.soundbeatsartifact.domain.usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.usuario.modelo.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNombre(String nombre);

    Usuario findByApellido(String apellido);
}
