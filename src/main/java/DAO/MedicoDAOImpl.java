package DAO;
import entity.Mascota;
import entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class MedicoDAOImpl implements MedicoDAO{
    private final EntityManager entityManager;

    public MedicoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void crearMedico(Medico medico) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(medico);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Medico obtenerMedicoPorId(Long id) {
        return entityManager.find(Medico.class, id);
    }

    @Override
    public List<Medico> obtenerTodosLosMedicos() {
        Query query = entityManager.createQuery("SELECT m FROM Medico m");
        return query.getResultList();
    }

    @Override
    public void actualizarMedico(Medico medico) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(medico);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarMedico(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Medico medico = entityManager.find(Medico.class, id);
            if (medico != null) {
                entityManager.remove(medico);
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
