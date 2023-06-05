package soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;

public class Consulta {
    Integer consultaid;
    String fecha;
    String ciudad;
    String direccion;
    String enfermedad;
    String nombreMedico;
    Integer verificado;
    String audio;
    String nuss;
    Integer farmaciaID;
    Integer hospitalID;
    Paciente pac;

    public Consulta() {
    }

    public Consulta(Integer consultaid, String fecha, String ciudad, String direccion, String enfermedad, String nombreMedico, Integer verificado, String audio, String nuss, Integer hospitalID, Integer farmaciaID, Paciente pac) {
        this.consultaid = consultaid;
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.enfermedad = enfermedad;
        this.nombreMedico = nombreMedico;
        this.verificado = verificado;
        this.audio = audio;
        this.nuss = nuss;
        this.farmaciaID = farmaciaID;
        this.hospitalID = hospitalID;
        this.pac = pac;
    }

    public Integer getConsultaid() {
        return this.consultaid;
    }

    public void setConsultaid(Integer consultaid) {
        this.consultaid = consultaid;
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

    public String getEnfermedad() {
        return this.enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getNombreMedico() {
        return this.nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public Integer getVerificado() {
        return this.verificado;
    }

    public void setVerificado(Integer verificado) {
        this.verificado = verificado;
    }

    public String getAudio(){
        return this.audio;
    }

    public void setAudio(String audio){
        this.audio=audio;
    }

    public String getNuss(){
        return this.nuss;
    }

    public void setNuss(String nuss){
        this.nuss=nuss;
    }

    public Integer getFarmaciaID() {
        return this.farmaciaID;
    }

    public void setFarmaciaID(Integer farmaciaID) {
        this.farmaciaID = farmaciaID;
    }

    public Integer getHospitalID() {
        return this.hospitalID;
    }

    public void setHospitalID(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }


    public Paciente getPac() {
        return this.pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
    }

    @Override
    public String toString() {
        return "{" +
            " nuss='" + getNuss() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", ciudad='" + getCiudad() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", enfermedad='" + getEnfermedad() + "'" +
            ", nombreMedico='" + getNombreMedico() + "'" +
            ", verificado='" + getVerificado() + "'" +
            "}";
    }
}
