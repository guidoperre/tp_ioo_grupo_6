package app;

import models.EstadoResultado;
import models.Rol;
import models.Sexo;
import navigation.NavigationManager;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticasTable;
import ui.resultados.model.Resultado;
import ui.sucursales.model.Sucursal;
import ui.usuarios.model.Usuario;
import ui.usuarios.model.UsuariosTable;
import ui.login.Login;
import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacientesTable;
import ui.sucursales.model.SucursalesTable;
import utils.DataUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Esta es la clase principal de la aplicacion. Va a iniciar en la pantalla de login. Aqui se deben ejecutar todas
 * las instancias de configuracion previas.
*/
public class Application {

    private static final JFrame frame = new JFrame("MedicinaBinaria");
    public static final NavigationManager manager = new NavigationManager(frame);

    public static void main(String[] args){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        manager.navigateTo(new Login());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.pack();

        //Calculate the frame location
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        frame.setLocation(x, y);
        frame.setVisible(true);

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
                        new ArrayList<>(),
                        1
                )
        );
        PracticasTable.addPractica(
                new Practica(
                        2,
                        true,
                        "Analisis globulos rojos",
                        new ArrayList<>(),
                        new ArrayList<>(),
                        1
                )
        );
        PracticasTable.addPractica(
                new Practica(
                        3,
                        true,
                        "Radiografia torax",
                        new ArrayList<>(),
                        new ArrayList<>(),
                        1
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
                        Sexo.MASCULINO
                )
        );
        PacientesTable.addPaciente(
                new Paciente(
                        "Tomas Canzoniero",
                        42341208,
                        "Siempre viva 742",
                        "perreguido@gmail.com",
                        21,
                        Sexo.MASCULINO
                )
        );
        PacientesTable.addPaciente(
                new Paciente(
                        "Agustin Maio",
                        42341208,
                        "Siempre viva 742",
                        "perreguido@gmail.com",
                        21,
                        Sexo.MASCULINO
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
        List<Paciente> pacientes = PacientesTable.getAllPacientes();
        Sucursal sucursal = SucursalesTable.getAllSucursales().get(0);

        List<Resultado> resultados1 = new ArrayList<>();
        resultados1.add(
                new Resultado(
                        1F,
                        EstadoResultado.PENDIENTE,
                        1
                )
        );

        List<Practica> PracticaPeticion1 = new ArrayList<>();
        PracticaPeticion1.addAll(practias);

        PeticionesTable.addPeticiones(
            new Peticion(
                    pacientes.get(0),
                    "OSDE 310",
                    sucursal,
                    new Date(),
                    new Date(),
                    PracticaPeticion1,
                    resultados1
            )
        );

        List<Practica> PracticaPeticion2 = new ArrayList<>();
        PracticaPeticion2.addAll(practias);

        List<Resultado> resultados2 = new ArrayList<>();
        resultados2.add(
                new Resultado(
                        1F,
                        EstadoResultado.FINALIZADO,
                        2
                )
        );
        PeticionesTable.addPeticiones(
                new Peticion(
                        pacientes.get(1),
                        "OSDE 410",
                        sucursal,
                        new Date(),
                        new Date(),
                        PracticaPeticion2,
                        resultados2
                )
        );

        List<Practica> PracticaPeticion3 = new ArrayList<>();
        PracticaPeticion3.addAll(practias);

        List<Resultado> resultados3 = new ArrayList<>();
        resultados3.add(
            new Resultado(
                1F,
                EstadoResultado.PENDIENTE,
                3
            )
        );
        PeticionesTable.addPeticiones(
                new Peticion(
                        pacientes.get(2),
                        "OSDE 510",
                        sucursal,
                        new Date(),
                        new Date(),
                        PracticaPeticion3,
                        resultados3
                )
        );
    }
}
