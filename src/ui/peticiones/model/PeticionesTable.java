package ui.peticiones.model;

import ui.pacientes.models.Paciente;
import ui.pacientes.models.PacienteDTO;
import ui.practicas.model.Practica;
import ui.practicas.model.PracticaDTO;
import ui.resultados.model.Resultado;
import ui.resultados.model.ResultadoDTO;
import ui.sucursales.model.Sucursal;
import ui.usuarios.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PeticionesTable {

    private static ArrayList<PeticionDTO> peticiones;
    private static Long indexCount = 0L;

    public static void init() {
        peticiones = new ArrayList<>();
    }

    public static List<PeticionDTO> getAllPeticiones() {
        return peticiones;
    }

    public static List<PeticionDTO> getAllPeticionesPaciente(PacienteDTO paciente) {
        List<PeticionDTO> peticionesPaciente = new ArrayList<>();
        for (PeticionDTO p: peticiones) {
            if (p.getPaciente().equals(paciente)) {
                peticionesPaciente.add(p);
            }
        }
        return peticionesPaciente;
    }

    public static List<PeticionDTO> getPeticionesCriticas() {
        List<PeticionDTO> aux = new ArrayList<>();
        for (PeticionDTO peticion: peticiones) {
            ArrayList<Resultado> resultados = new ArrayList<>();
            for (ResultadoDTO resultadoDTO: peticion.getResultados()) {
                resultados.add(new Resultado(
                        resultadoDTO.getValor(),
                        resultadoDTO.getEstado(),
                        resultadoDTO.getCodigoPractica()
                ));
            }
            ArrayList<Practica> practicas = new ArrayList<>();
            for (PracticaDTO practica: peticion.getPracticas()) {
                practicas.add(new Practica(
                        practica.getCodigo(),
                        practica.getActivo(),
                        practica.getNombre(),
                        practica.getHoras(),
                        practica.getValoresCriticos(),
                        practica.getValoresReservados()
                ));
            }
            if (new Peticion(
                    new Paciente(peticion.getPaciente().getNombre(), peticion.getPaciente().getDni(), peticion.getPaciente().getDomicilio(), peticion.getPaciente().getMail(), peticion.getPaciente().getEdad(), peticion.getPaciente().getSexo()),
                    peticion.getObraSocial(),
                    new Sucursal(
                            peticion.getSucursal().getDireccion(),
                            new Usuario(
                                    peticion.getSucursal().getResponsable().getNombre(),
                                    peticion.getSucursal().getResponsable().getDni(),
                                    peticion.getSucursal().getResponsable().getDomicilio(),
                                    peticion.getSucursal().getResponsable().getMail(),
                                    peticion.getSucursal().getResponsable().getUsername(),
                                    peticion.getSucursal().getResponsable().getPassword(),
                                    peticion.getSucursal().getResponsable().getFechaNacimiento(),
                                    peticion.getSucursal().getResponsable().getRol()
                            )
                    ),
                    peticion.getFechaCarga(),
                    peticion.getFechaEntrega(),
                    practicas,
                    resultados
            ).isCritica()) {
                aux.add(peticion);
            }
        }
        return aux;
    }


    public static PeticionDTO getPeticiones(String nombre) {
        for (PeticionDTO p: peticiones) {
            if (p.getPaciente().getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    public static void addPeticiones(PeticionDTO peticion) {
        peticion.setId(indexCount);
        peticiones.add(peticion);
        indexCount++;
    }

    public static void modifyPeticiones(PeticionDTO peticion) {
        for (int i = 0; i < peticiones.size(); i++) {
            PeticionDTO aux = peticiones.get(i);
            if (Objects.equals(aux.getId(), peticion.getId())) {
                peticiones.remove(i);
                peticiones.add(peticion);
                return;
            }
        }
    }

    public static void deletePeticiones(PeticionDTO peticion) {
        for (int i = 0; i < peticiones.size(); i++) {
            PeticionDTO aux = peticiones.get(i);
            if (Objects.equals(aux.getId(), peticion.getId())) {
                peticiones.remove(i);
                break;
            }
        }
    }
}
