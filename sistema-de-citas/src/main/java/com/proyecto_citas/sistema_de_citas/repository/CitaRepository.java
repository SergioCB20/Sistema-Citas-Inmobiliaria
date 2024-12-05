package com.proyecto_citas.sistema_de_citas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto_citas.sistema_de_citas.model.Cita;
import java.util.List;
import com.proyecto_citas.sistema_de_citas.model.Cliente;
import java.util.UUID;
import com.proyecto_citas.sistema_de_citas.model.Agente;
import com.proyecto_citas.sistema_de_citas.enums.EstadoCita;

public interface CitaRepository extends JpaRepository<Cita, UUID> {

    List<Cita> findByCliente(Cliente cliente);

    List<Cita> findByAgenteAsignado(Agente agente);

    List<Cita> findByEstadoCita(EstadoCita estado);
}

