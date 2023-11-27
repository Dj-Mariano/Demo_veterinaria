package entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<TurnoMedico> turnos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<TurnoMedico> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<TurnoMedico> turnos) {
        this.turnos = turnos;
    }
}