import models.Rol;
import models.SexoEnum;
import models.Usuario;
import models.UsuariosTable;
import ui.login.Login;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.sucursales.model.SucursalesTable;

import java.util.Date;

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

        createUsuarios();
        createPacientes();
    }

    private static void createUsuarios() {
        UsuariosTable.addUsuario(
                new Usuario(
                        "Admin",
                        12345678,
                        "Calle 123",
                        "mail@mail.com",
                        "admin",
                        "admin",
                        new Date(),
                        Rol.ADMINISTRADOR
                )
        );
    }

    private static void createPacientes() {
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
