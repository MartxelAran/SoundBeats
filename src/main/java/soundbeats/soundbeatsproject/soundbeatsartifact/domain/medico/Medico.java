package soundbeats.soundbeatsproject.soundbeatsartifact.domain.medico;

public class Medico {
    String nombrecompleto;
    String password;
    String dni;

    public Medico() {
    }

    public Medico(String nombrecompleto, String password, String dni) {
        this.nombrecompleto = nombrecompleto;
        this.password = password;
        this.dni = dni;
    }

    public String getNombrecompleto() {
        return this.nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
