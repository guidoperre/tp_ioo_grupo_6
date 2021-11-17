package ui.home;

import app.Application;
import models.Rol;
import navigation.Screen;
import ui.login.Login;
import ui.pacientes.Pacientes;
import ui.practicas.Practicas;
import ui.peticiones.Peticiones;
import ui.resultados.Resultados;
import ui.sucursales.Sucursales;
import ui.usuarios.Usuarios;
import ui.usuarios.model.UsuariosTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Pantalla principal de medicina binaria
 */
public class Home implements Screen {

    private JLabel exitButton;
    private JPanel panel;
    private JLabel practicaLogo;
    private JLabel sucursalesLogo;
    private JLabel pacienteLogo;
    private JLabel peticionesLogo;
    private JLabel resultadosLogo;
    private JLabel usuariosLogo;

    public Home() {

    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        // Evento click botonSalir
        setLogout();
        setPacientes();
        setSucursales();
        setUsuarios();
        setPeticiones();
        setResultados();
        setPracticas();
    }

    private void setLogout() {
        exitButton = new JLabel(new ImageIcon("resources/salir.png"));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UsuariosTable.usuario = null;
                Application.manager.navigateTo(new Login());
            }
        });
    }

    private void setPacientes() {
        if (UsuariosTable.usuario.getRol() == Rol.ADMINISTRADOR || UsuariosTable.usuario.getRol() == Rol.RECEPCION) {
            pacienteLogo = new JLabel(new ImageIcon("resources/pacientes.png"));
            pacienteLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Application.manager.navigateTo(new Pacientes());
                }
            });
        } else {
            pacienteLogo = new JLabel();
            pacienteLogo.setVisible(false);
        }
    }

    private void setSucursales() {
        if (UsuariosTable.usuario.getRol() == Rol.ADMINISTRADOR) {
            sucursalesLogo = new JLabel(new ImageIcon("resources/home.png"));
            sucursalesLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Application.manager.navigateTo(new Sucursales());
                }
            });
        } else {
            sucursalesLogo = new JLabel();
            sucursalesLogo.setVisible(false);
        }
    }

    private void setUsuarios() {
        if (UsuariosTable.usuario.getRol() == Rol.ADMINISTRADOR) {
            usuariosLogo = new JLabel(new ImageIcon("resources/usuarios.png"));
            usuariosLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Application.manager.navigateTo(new Usuarios());
                }
            });
        } else {
            usuariosLogo = new JLabel();
            usuariosLogo.setVisible(false);
        }
    }

    private void setPeticiones() {
        if (UsuariosTable.usuario.getRol() == Rol.ADMINISTRADOR || UsuariosTable.usuario.getRol() == Rol.RECEPCION) {
            peticionesLogo = new JLabel(new ImageIcon("resources/peticiones.png"));
            peticionesLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Application.manager.navigateTo(new Peticiones());
                }
            });
        } else {
            peticionesLogo = new JLabel();
            peticionesLogo.setVisible(false);
        }
    }

    private void setPracticas() {
        if (UsuariosTable.usuario.getRol() == Rol.ADMINISTRADOR || UsuariosTable.usuario.getRol() == Rol.LABORATORIO) {
            practicaLogo = new JLabel(new ImageIcon("resources/practicas.png"));
            practicaLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Application.manager.navigateTo(new Practicas());
                }
            });
        } else {
            practicaLogo = new JLabel();
            practicaLogo.setVisible(false);
        }
    }

    private void setResultados() {
        resultadosLogo = new JLabel(new ImageIcon("resources/resultados.png"));
        resultadosLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.manager.navigateTo(new Resultados());
            }
        });
    }

}
