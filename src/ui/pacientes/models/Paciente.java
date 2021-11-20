package ui.pacientes.models;

import models.PersonaDTO;
import models.Sexo;
import ui.peticiones.model.Peticion;
import ui.peticiones.model.PeticionDTO;
import ui.peticiones.model.PeticionesTable;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;
import ui.sucursales.model.Sucursal;
import ui.usuarios.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paciente extends PersonaDTO {

    private int edad;
    private Sexo sexo;

    public Paciente(String nombre, int dni, String domicilio, String mail, int edad, Sexo sexo) {
        super(nombre, dni, domicilio, mail);
        this.edad = edad;
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return edad == paciente.edad &&
                sexo == paciente.sexo &&
                paciente.getNombre().equals(getNombre()) &&
                paciente.getDni() == getDni() &&
                paciente.getDomicilio().equals(getDomicilio()) &&
                paciente.getMail().equals(getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(edad, sexo, getNombre(), getDni(), getDomicilio(), getMail());
    }

    @Override
    public String toString() {
        return getNombre();
    }

    public List<Peticion> getPeticionesFinalizadas() {
        List<Peticion> peticiones = new ArrayList<>();
        for (PeticionDTO peticionDTO: PeticionesTable.getAllPeticiones()) {
            ArrayList<Resultado> resultados = new ArrayList<>();
            for (ResultadoDTO resultadoDTO: peticionDTO.getResultados()) {
                resultados.add(new Resultado(
                        resultadoDTO.getValor(),
                        resultadoDTO.getEstado(),
                        resultadoDTO.getCodigoPractica()
                ));
            }
            ArrayList<Practica> practicas = new ArrayList<>();
            for (PracticaDTO practica: peticionDTO.getPracticas()) {
                practicas.add(new Practica(
                        practica.getCodigo(),
                        practica.getActivo(),
                        practica.getNombre(),
                        practica.getHoras(),
                        practica.getValoresCriticos(),
                        practica.getValoresReservados()
                ));
            }
            Peticion peticion = new Peticion(
                    new Paciente(
                            peticionDTO.getPaciente().getNombre(),
                            peticionDTO.getPaciente().getDni(),
                            peticionDTO.getPaciente().getDomicilio(),
                            peticionDTO.getPaciente().getMail(),
                            peticionDTO.getPaciente().getEdad(),
                            peticionDTO.getPaciente().getSexo()),
                    peticionDTO.getObraSocial(),
                    new Sucursal(
                            peticionDTO.getSucursal().getDireccion(),
                            new Usuario(
                                    peticionDTO.getSucursal().getResponsable().getNombre(),
                                    peticionDTO.getSucursal().getResponsable().getDni(),
                                    peticionDTO.getSucursal().getResponsable().getDomicilio(),
                                    peticionDTO.getSucursal().getResponsable().getMail(),
                                    peticionDTO.getSucursal().getResponsable().getUsername(),
                                    peticionDTO.getSucursal().getResponsable().getPassword(),
                                    peticionDTO.getSucursal().getResponsable().getFechaNacimiento(),
                                    peticionDTO.getSucursal().getResponsable().getRol()
                            )
                    ),
                    peticionDTO.getFechaCarga(),
                    peticionDTO.getFechaEntrega(),
                    practicas,
                    resultados

            );
            if (peticion.getPaciente().getId() == this.getId()) {
                if (peticion.isFinalizada())
                    peticiones.add(peticion);
            }
        }
        return peticiones;
    }
}
