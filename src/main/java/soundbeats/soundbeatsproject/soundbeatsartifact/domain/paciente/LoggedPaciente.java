package soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente;

public class LoggedPaciente {
    static Paciente loggedPaciente;

    public static void setPaciente(Paciente paciente){
        loggedPaciente=paciente;
    }

    public static Paciente getPaciente(){
        return loggedPaciente;
    }
}
