package soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos;

public class Enfermedad {
    
    String nombre;
    String definicion;

    public Enfermedad(String nombre, String definicion) {
        this.nombre = nombre;
        this.definicion = definicion;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDefinicion() {
        return this.definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

}
