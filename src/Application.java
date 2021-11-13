import models.SexoEnum;
import ui.login.Login;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;

/**
 * Esta es la clase principal de la aplicacion. Va a iniciar en la pantalla de login. Aqui se deben ejecutar todas
 * las instancias de configuracion previas.
*/
public class Application {

    public static void main(String[] args){
        new Login();
        createPacientes();
    }

    private static void createPacientes() {
        PacientesTable.init();
        for (int i = 0; i < 4; i++) {
            PacientesTable.addPaciente(
                    new Paciente(
                            "Guido Perre",
                            42341208,
                            "Siempre viva 742",
                            "perreguido@gmail.com",
                            21,
                            SexoEnum.MASCULINO
                    )
            );
        }
    }
}
