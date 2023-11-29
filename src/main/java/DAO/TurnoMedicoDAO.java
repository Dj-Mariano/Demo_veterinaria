package DAO;
import entity.TurnoMedico;

import java.util.List;

public interface TurnoMedicoDAO {
    void crearTurnoMedico(TurnoMedico turnoMedico);
    TurnoMedico obtenerTurnoMedicoPorId(Long id);
    List<TurnoMedico> obtenerTodosLosTurnoMedicos();
    List<TurnoMedico> obtenerTurnosLibres();
    void actualizarTurnoMedico(TurnoMedico turnoMedico);
    void eliminarTurnoMedico(Long id);
}
