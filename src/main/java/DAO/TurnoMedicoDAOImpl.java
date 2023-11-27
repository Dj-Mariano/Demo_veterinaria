package DAO;
import entity.TurnoMedico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class TurnoMedicoDAOImpl implements TurnoMedicoDAO{
    private final EntityManager entityManager;

    public TurnoMedicoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void crearTurnoMedico(TurnoMedico turnoMedico) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(turnoMedico);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public TurnoMedico obtenerTurnoMedicoPorId(Long id) {
        return entityManager.find(TurnoMedico.class, id);
    }

    @Override
    public List<TurnoMedico> obtenerTodosLosTurnoMedicos() {
        Query query = entityManager.createQuery("SELECT m FROM TurnoMedico m");
        return query.getResultList();
    }

    @Override
    public void actualizarTurnoMedico(TurnoMedico turnoMedico) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(turnoMedico);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTurnoMedico(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TurnoMedico turnoMedico = entityManager.find(TurnoMedico.class, id);
            if (turnoMedico != null) {
                entityManager.remove(turnoMedico);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
