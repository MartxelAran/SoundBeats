package soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos;

public class Enfermedad {
    
    String nombre;
    String solucion;

    public Enfermedad(String nombre, String solucion) {
        this.nombre = nombre;
        this.solucion = solucion;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSolucion() {
        return this.solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

}
