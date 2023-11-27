import DAO.*;
import InterfazUsuario.VeterinariaInterfaz;
import entity.Gato;
import entity.Mascota;
import entity.Medico;
import entity.TurnoMedico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import service.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        MascotaDAO mascotaDAO = new MascotaDAOImpl(entityManager);
        MascotaService mascotaService = new MascotaServiceImpl(mascotaDAO);

        TurnoMedicoDAO turnoMedicoDAO = new TurnoMedicoDAOImpl(entityManager);
        TurnoMedicoService turnoMedicoService = new TurnoMedicoServiceImpl(turnoMedicoDAO);

        MedicoDAO medicoDAO = new MedicoDAOImpl(entityManager);
        MedicoService medicoService = new MedicoServiceImpl(medicoDAO);

        VeterinariaInterfaz veterinaria = new VeterinariaInterfaz(mascotaService, medicoService, turnoMedicoService);
        veterinaria.iniciar();


    }
}
