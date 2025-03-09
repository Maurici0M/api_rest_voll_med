package med.voll.api.repository;

import med.voll.api.domain.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {

    /*
    * Cria um metodo para buscar os médicos pelo ddd;
    * lembre de deixar a nomenclatura igual às presentes no jpa como findBy, por exemplo;
    * o findByEnderecoDdd significa que quero buscar um ddd presente na tabela de endereços, que tenha relação com os médicos, através do nosso ManyToOne entre médicos e endereços
    * */

    List<Medicos> findByEnderecoDdd(String ddd);

}
