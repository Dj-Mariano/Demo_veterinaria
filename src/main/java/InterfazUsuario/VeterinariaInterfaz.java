package InterfazUsuario;
import DAO.MascotaDAO;
import DAO.MascotaDAOImpl;
import DAO.TurnoMedicoDAO;
import DAO.TurnoMedicoDAOImpl;
import entity.*;
import service.MascotaService;
import service.MedicoService;
import service.TurnoMedicoService;
import service.TurnoMedicoServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class VeterinariaInterfaz {
    private Scanner scanner;
    private final MascotaService mascotaService;
    private final MedicoService medicoService;
    private final TurnoMedicoService turnoMedicoService;
    public VeterinariaInterfaz(MascotaService mascotaService, MedicoService medicoService, TurnoMedicoService turnoMedicoService) {
        this.scanner = new Scanner(System.in);
        this.mascotaService = mascotaService;
        this.medicoService = medicoService;
        this.turnoMedicoService = turnoMedicoService;
    }

    public void iniciar() {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Sacar turno");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarMascota();
                    break;
                case 2:
                    sacarTurno(null);
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void registrarMascota() {
        Mascota nuevaMascota = new Mascota();

        System.out.println("Ingrese el nombre de la mascota:");
        String nombre = scanner.nextLine();
        if(nombre.isEmpty()){
            System.out.println("Nombre invalido");}

        System.out.println("Ingrese la raza de la mascota:");
        String raza = scanner.nextLine();
        if(nombre.isEmpty()){
            System.out.println("raza invalida");}

        System.out.println("Ingrese el color del pelo de la mascota:");
        String colorPelo = scanner.nextLine();
        if(nombre.isEmpty()){
            System.out.println("Color de pelo invalido");}

        System.out.println("Ingrese la edad de la mascota:");
        int edad = scanner.nextInt();
        if(nombre.isEmpty()){
            System.out.println("Edad invalida");}

        System.out.println("Ingrese el peso de la mascota:");
        double peso = scanner.nextDouble();
        if(nombre.isEmpty()){
            System.out.println("Peso invalido");
        }

        System.out.println("Seleccione el tipo de mascota (1: Perro, 2: Gato):");
        int tipoMascota = scanner.nextInt();


        switch (tipoMascota) {
            case 1:
                nuevaMascota = crearPerro(nombre, raza, colorPelo, edad, peso);
                registrarMascota(nuevaMascota);
                break;
            case 2:
                nuevaMascota = crearGato(nombre, raza, colorPelo, edad, peso);
                registrarMascota(nuevaMascota);
                break;
            default:
                System.out.println("Tipo de mascota no válido.");
        }

        System.out.println("Desea asignarle un turno a su mascota? (1: Si, 2: NO)");
        int entrada = scanner.nextInt();
        if(entrada==1){
            sacarTurno(nuevaMascota);
        }
    }

    private void sacarTurno(Mascota mascota) {
        Mascota mascotaNueva = new Mascota();
        if(mascota==null){
            System.out.println("Ingrese el nombre de la mascota:");
            String nombre = scanner.nextLine();
            List<Mascota> mascotas = mascotaService.buscarPorNombre(nombre);
            if(mascotas.isEmpty()){
                System.out.println("No se encuentran mascotas con el nombre: " + nombre);
                System.out.println();
            }else if(mascotas.size()>1){
                System.out.println("Se han encontrado " + mascotas.size() + " mascotas con el nombre: " + nombre);
                for(Mascota m : mascotas){
                    System.out.println(m.toString() + " - Nombre: " + m.getNombre() + " - Id " + m.getId() + " - Raza: " + m.getRaza());
                }
                System.out.println();
                scanner.nextLine();
                System.out.println("Ingrese el Id de la mascota que desea");
                int idMascota = scanner.nextInt();
                mascotaNueva = mascotaService.obtenerMascotaPorId((long) idMascota);
            } else{
                mascotaNueva = mascotas.get(0);
            }

            TurnoMedico miTurno = new TurnoMedico();
            miTurno.setFechaTurno(new Date());
            Medico medico = medicoService.obtenerMedicoPorId(1L);
            miTurno.setMedico(medico);
            miTurno.setMascota(mascotaNueva);
            turnoMedicoService.crearTurnoMedico(miTurno);
        }

    }

    private Perro crearPerro(String nombre, String raza, String colorPelo, int edad, double peso) {
        Perro nuevoPerro = new Perro();
        nuevoPerro.setNombre(nombre);
        nuevoPerro.setRaza(raza);
        nuevoPerro.setColorPelo(colorPelo);
        nuevoPerro.setEdad(edad);
        nuevoPerro.setPeso(peso);
        return nuevoPerro;
    }
    private Gato crearGato(String nombre, String raza, String colorPelo, int edad, double peso) {
        Gato nuevoGato = new Gato();
        nuevoGato.setNombre(nombre);
        nuevoGato.setRaza(raza);
        nuevoGato.setColorPelo(colorPelo);
        nuevoGato.setEdad(edad);
        nuevoGato.setPeso(peso);
        return nuevoGato;
    }
    private void registrarMascota(Mascota mascota) {
        if (mascota != null) {
            try {
                mascotaService.crearMascota(mascota);
                System.out.println("Mascota registrada exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al registrar la mascota: " + e.getMessage());
            }
        }
    }
}
