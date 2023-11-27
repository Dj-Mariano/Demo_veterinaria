package DAO;

import entity.Medico;

import java.util.List;

public interface MedicoDAO {
    void crearMedico(Medico medico);
    Medico obtenerMedicoPorId(Long id);
    List<Medico> obtenerTodosLosMedicos();
    void actualizarMedico(Medico medico);
    void eliminarMedico(Long id);
}
