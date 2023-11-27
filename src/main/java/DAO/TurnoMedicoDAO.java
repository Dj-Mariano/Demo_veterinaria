package DAO;
import entity.TurnoMedico;

import java.util.List;

public interface TurnoMedicoDAO {
    void crearTurnoMedico(TurnoMedico turnoMedico);
    TurnoMedico obtenerTurnoMedicoPorId(Long id);
    List<TurnoMedico> obtenerTodosLosTurnoMedicos();
    void actualizarTurnoMedico(TurnoMedico turnoMedico);
    void eliminarTurnoMedico(Long id);
}
