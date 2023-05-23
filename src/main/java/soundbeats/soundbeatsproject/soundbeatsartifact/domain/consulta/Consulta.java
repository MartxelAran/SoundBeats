package soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Consulta {
    String fecha;
    String ciudad;
    String direccion;
    String nombreEnfermedad;
    String nombreMedico;
    Integer verificado;
    String audio;


    public Consulta() {
    }

    public Consulta(String fecha, String ciudad, String direccion, String nombreEnfermedad, String nombreMedico, Integer verificado, String audio) {
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombreEnfermedad = nombreEnfermedad;
        this.nombreMedico = nombreMedico;
        this.verificado = verificado;
        this.audio = audio;
    }


    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreEnfermedad() {
        return this.nombreEnfermedad;
    }

    public void setNombreEnfermedad(String nombreEnfermedad) {
        this.nombreEnfermedad = nombreEnfermedad;
    }

    public String getNombreMedico() {
        return this.nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public Integer isverificado() {
        return this.verificado;
    }

    public Integer getverificado() {
        return this.verificado;
    }

    public void setverificado(Integer verificado) {
        this.verificado = verificado;
    }

    public String getAudio(){
        return this.audio;
    }

    public void setAudio(String audio){
        this.audio=audio;
    }


    @Override
    public String toString() {
        return "{" +
            " fecha='" + getFecha() + "'" +
            ", ciudad='" + getCiudad() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", nombreEnfermedad='" + getNombreEnfermedad() + "'" +
            ", nombreMedico='" + getNombreMedico() + "'" +
            ", verificado='" + isverificado() + "'" +
            "}";
    }
}
