import models.Rol;
import models.SexoEnum;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.sucursales.model.Sucursal;
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
import java.util.List;

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
        UsuariosTable.init();
        PacientesTable.init();
        PracticasTable.init();
        SucursalesTable.init();
        PeticionesTable.init();

        createUsuarios();
        createPacientes();
        createPracticas();
        createSucursales();
        createPeticiones();
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
        UsuariosTable.addUsuario(
                new Usuario(
                        "Admin",
                        12345678,
                        "Calle 123",
                        "mail@mail.com",
                        "laboratorio",
                        "123",
                        calendar.getTime(),
                        Rol.LABORATORIO
                )
        );
        UsuariosTable.addUsuario(
                new Usuario(
                        "Admin",
                        12345678,
                        "Calle 123",
                        "mail@mail.com",
                        "recepcion",
                        "123",
                        calendar.getTime(),
                        Rol.RECEPCION
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

    private static void createSucursales() {
        Usuario usuario = UsuariosTable.getAllUsuarios().get(0);
        SucursalesTable.addSucursal(
            new Sucursal(
                "Peribebuy 7032",
                usuario,
                new ArrayList<>()
            )
        );
        SucursalesTable.addSucursal(
                new Sucursal(
                        "Lisandro de la torre 111",
                        usuario,
                        new ArrayList<>()
                )
        );
        SucursalesTable.addSucursal(
                new Sucursal(
                        "Timoteo gordillo 713",
                        usuario,
                        new ArrayList<>()
                )
        );
    }

    private static void createPeticiones() {
        List<Practica> practias = PracticasTable.getAllPracticas();
        Paciente paciente = PacientesTable.getAllPacientes().get(0);
        Sucursal sucursal = SucursalesTable.getAllSucursales().get(0);
        PeticionesTable.addPeticiones(
            new Peticion(
                    paciente,
                    "OSDE 310",
                    sucursal,
                    new Date(),
                    new Date(),
                    practias,
                    new ArrayList<>()
            )
        );
        PeticionesTable.addPeticiones(
                new Peticion(
                        paciente,
                        "OSDE 410",
                        sucursal,
                        new Date(),
                        new Date(),
                        practias,
                        new ArrayList<>()
                )
        );
        PeticionesTable.addPeticiones(
                new Peticion(
                        paciente,
                        "OSDE 510",
                        sucursal,
                        new Date(),
                        new Date(),
                        practias,
                        new ArrayList<>()
                )
        );
    }
}
