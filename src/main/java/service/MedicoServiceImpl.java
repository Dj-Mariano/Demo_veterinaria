package service;
import DAO.MedicoDAO;
import entity.Medico;
import java.util.List;

public class MedicoServiceImpl implements MedicoService{
    private final MedicoDAO medicoDAO;
    public MedicoServiceImpl(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }

    @Override
    public void crearMedico(Medico medico) {
        medicoDAO.crearMedico(medico);
    }

    @Override
    public Medico obtenerMedicoPorId(Long id) {
        return medicoDAO.obtenerMedicoPorId(id);
    }

    @Override
    public List<Medico> obtenerTodosLosMedicos() {
        return medicoDAO.obtenerTodosLosMedicos();
    }

    @Override
    public void actualizarMedico(Medico medico) {
        medicoDAO.actualizarMedico(medico);
    }

    @Override
    public void eliminarMedico(Long id) {
        medicoDAO.eliminarMedico(id);
    }
}
