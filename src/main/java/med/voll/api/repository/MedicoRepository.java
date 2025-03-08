package med.voll.api.repository;

import med.voll.api.domain.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {
}
