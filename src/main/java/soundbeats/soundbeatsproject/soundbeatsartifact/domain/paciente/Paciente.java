package soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente;


public class Paciente {
    String nombre;
    String apellido;
    String numss;
    String fechanacimiento;
    String dni;
    String ciudad;
    Integer edad;
    String medico;


    public Paciente(String nombre, String apellido, String numss, String fechanacimiento, String dni, String ciudad, Integer edad, String medico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numss = numss;
        this.fechanacimiento = fechanacimiento;
        this.dni = dni;
        this.ciudad = ciudad;
        this.edad = edad;
        this.medico = medico;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumss() {
        return this.numss;
    }

    public void setNumss(String numss) {
        this.numss = numss;
    }

    public String getFechanacimiento() {
        return this.fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombreMedico(){
        return this.medico;
    }

    @Override
    public String toString() {
        return
            "Nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", numss='" + getNumss() + "'" +
            ", fechanacimiento='" + getFechanacimiento() + "'" +
            ", dni='" + getDni() + "'" +
            ", ciudad='" + getCiudad() + "'" +
            ", edad='" + getEdad();
    }
}
