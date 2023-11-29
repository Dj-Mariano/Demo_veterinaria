package InterfazUsuario;
import DAO.*;
import entity.*;
import service.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class VeterinariaInterfaz {
    private Scanner scanner;
    private final MascotaService mascotaService;
    private final MedicoService medicoService;
    private final TurnoMedicoService turnoMedicoService;

    public VeterinariaInterfaz() {
        this.scanner = new Scanner(System.in);
        MascotaDAO mascotaDAO = new MascotaDAOImpl();
        mascotaService = new MascotaServiceImpl(mascotaDAO);
        TurnoMedicoDAO turnoMedicoDAO = new TurnoMedicoDAOImpl();
        turnoMedicoService = new TurnoMedicoServiceImpl(turnoMedicoDAO);
        MedicoDAO medicoDAO = new MedicoDAOImpl();
        medicoService = new MedicoServiceImpl(medicoDAO);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Sacar turno");
            System.out.println("3. Ingresar como médico");
            System.out.println("4. Registrar Medico");
            System.out.println("5. Salir");

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
                    ingresarComoMedico();
                    break;
                case 4:
                    registrarMedico();
                    break;
                case 5:
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
                agregarMascota(nuevaMascota);
                break;

            case 2:
                nuevaMascota = crearGato(nombre, raza, colorPelo, edad, peso);
                agregarMascota(nuevaMascota);
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

    private void registrarMedico(){
        List<Medico> medicoList = medicoService.obtenerTodosLosMedicos();
        String nombreUsuario = new String();
        String contraseña;
        clearConsola();
        Scanner scanner = new Scanner(System.in);

        boolean estado = true;
        while (estado){
            boolean nombreUsuarioExistente = false;
            System.out.print("Ingrese el nombre de usuario: ");
            nombreUsuario = scanner.nextLine();

            for(Medico m : medicoList){
                if(m.getNombre().equals(nombreUsuario)){
                    nombreUsuarioExistente = true;
                    System.out.println("El nombre " + nombreUsuario + " ya esta registrado");
                    System.out.println();
                    System.out.println();
                    break;
                }
            }
            if (!nombreUsuarioExistente) {
                estado = false;
            }
        }

        System.out.print("Ingrese la contraseña:");
        contraseña = scanner.nextLine();

        Medico nuevoMedico = new Medico();
        try {
            nuevoMedico.setNombre(nombreUsuario);
            nuevoMedico.setContraseña(contraseña);
            medicoService.crearMedico(nuevoMedico);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el médico. Por favor, inténtelo nuevamente.");
        }
        clearConsola();
        System.out.println("Registro exitoso.");
        System.out.println();
    }

    private void ingresarComoMedico(){
        clearConsola();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        Medico medico = medicoService.obtenerMedicoPorNombre(nombreUsuario);

        if(medico == null) {
            System.out.println();
            System.out.println("Nombre de usuario incorrecto");
            clearConsola();
            return;
        } else{
            System.out.print("Ingrese su contraseña: ");
            String contrasena = scanner.nextLine();
            if(medico.getContraseña().equals(contrasena)){
                System.out.println("Bienvenido, Dr. " + nombreUsuario + "!");
            }
            else{
                System.out.println();
                System.out.println("Contraseña Incorrecta");
                clearConsola();
                return;
            }
        }
        clearConsola();
        List<TurnoMedico> turnosLibres = turnoMedicoService.obtenerTurnosLibres();
        if(turnosLibres.isEmpty()){
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            System.out.println("No Hay Turnos Disponibles");
            System.out.println();
            System.out.println("---------------------------------------------------------------");
            return;
        }
        System.out.println("Turnos Disponibles:");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-25s %-40s%n", "ID", "Fecha Turno", "Nombre de la Mascota");
        for(TurnoMedico turno : turnosLibres){
            System.out.printf("%-5d %-25s %-40s%n",
                    turno.getId(),
                    turno.getFechaTurno(),
                    (turno.getMascota() != null) ? turno.getMascota().getNombre() : "Sin Mascota");
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println();

        boolean estado = true;
        while (estado) {
            System.out.print("Ingrese el ID del turno que desea asignar (o 0 para salir): ");
            int id = scanner.nextInt();

            if (id == 0) {
                System.out.println("Operación cancelada.");
                break;
            }
            TurnoMedico nuevoTurno = turnoMedicoService.obtenerTurnoMedicoPorId((long) id);

            if (nuevoTurno != null) {
                nuevoTurno.setMedico(medico);
                turnoMedicoService.actualizarTurnoMedico(nuevoTurno);
                estado = false;
                clearConsola();
                System.out.println("Turno asignado correctamente.");
                System.out.println();
            } else {
                clearConsola();
                System.out.println("ID incorrecto. Por favor, ingrese un ID válido.");
            }
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
    private void agregarMascota(Mascota mascota) {
        if (mascota != null) {
            try {
                mascotaService.crearMascota(mascota);
                System.out.println("Mascota registrada exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al registrar la mascota: " + e.getMessage());
            }
        }
    }
    private static void clearConsola() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    }
