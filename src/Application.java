import models.Rol;
import models.SexoEnum;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
import ui.login.Login;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.sucursales.model.SucursalesTable;
import utils.DataUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Esta es la clase principal de la aplicacion. Va a iniciar en la pantalla de login. Aqui se deben ejecutar todas
 * las instancias de configuracion previas.
*/
public class Application {

    public static void main(String[] args){
        new Login();
        initTables();
    }

    private static void initTables() {
        PacientesTable.init();
        UsuariosTable.init();
        SucursalesTable.init();
        PeticionesTable.init();
        PracticasTable.init();

        createUsuarios();
        createPacientes();
        createPracticas();
    }

    private static void createUsuarios() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DataUtils.getFechaFromString("3/3/2000"));
        UsuariosTable.addUsuario(
                new Usuario(
                        "Admin",
                        12345678,
                        "Calle 123",
                        "mail@mail.com",
                        "admin",
                        "admin",
                        calendar.getTime(),
                        Rol.ADMINISTRADOR
                )
        );
    }

    private static void createPracticas(){
        PracticasTable.addPractica(
                new Practica(
                        1,
                        true,
                        "Analisis de sangre",
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
        PracticasTable.addPractica(
                new Practica(
                        2,
                        true,
                        "Analisis globulos rojos",
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
        PracticasTable.addPractica(
                new Practica(
                        3,
                        true,
                        "Radiografia torax",
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
    }

    private static void createPacientes() {
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
        PacientesTable.addPaciente(
                new Paciente(
                        "Tomas Canzoniero",
                        42341208,
                        "Siempre viva 742",
                        "perreguido@gmail.com",
                        21,
                        SexoEnum.MASCULINO
                )
        );
        PacientesTable.addPaciente(
                new Paciente(
                        "Agustin Maio",
                        42341208,
                        "Siempre viva 742",
                        "perreguido@gmail.com",
                        21,
                        SexoEnum.MASCULINO
                )
        );
    }
}
