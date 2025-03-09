package med.voll.api.interfaces;

import med.voll.api.domain.Medicos;

import java.util.List;

public interface IMedico {

    Medicos cadastrar(Medicos dados);

    List<Medicos> listarTodos();

    Medicos listarById(Long id);

    List<Medicos> listarByDdd(String ddd);

    Medicos excluir(Long id);

}
