package service;
import DAO.TurnoMedicoDAO;
import entity.TurnoMedico;
import java.util.List;

public class TurnoMedicoServiceImpl implements TurnoMedicoService{
    private final TurnoMedicoDAO turnoMedicoDAO;

    public TurnoMedicoServiceImpl(TurnoMedicoDAO turnoMedicoDAO) {
        this.turnoMedicoDAO = turnoMedicoDAO;
    }

    @Override
    public void crearTurnoMedico(TurnoMedico turnoMedico) {
        turnoMedicoDAO.crearTurnoMedico(turnoMedico);
    }

    @Override
    public TurnoMedico obtenerTurnoMedicoPorId(Long id) {
        return turnoMedicoDAO.obtenerTurnoMedicoPorId(id);
    }

    @Override
    public List<TurnoMedico> obtenerTodosLosTurnoMedicos() {
        return turnoMedicoDAO.obtenerTodosLosTurnoMedicos();
    }

    @Override
    public void actualizarTurnoMedico(TurnoMedico turnoMedico) {
        turnoMedicoDAO.actualizarTurnoMedico(turnoMedico);
    }

    @Override
    public void eliminarTurnoMedico(Long id) {
        turnoMedicoDAO.eliminarTurnoMedico(id);
    }
}
