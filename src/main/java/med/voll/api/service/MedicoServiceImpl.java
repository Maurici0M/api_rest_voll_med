package med.voll.api.service;

import med.voll.api.domain.Endereco;
import med.voll.api.domain.Medicos;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.interfaces.IMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;


@Service
public class MedicoServiceImpl implements IMedico {

    @Autowired
    private final MedicoRepository repository;

    public MedicoServiceImpl(MedicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medicos cadastrar(Medicos dados) {
        return repository.save(dados);
    }

    public DadosCadastroMedico cadastrarFormatado(Medicos medicos){
        return new DadosCadastroMedico(
                medicos.getNome(),
                medicos.getEmail(),
                medicos.getCrm(),
                medicos.getEspecialidade(),

                new DadosEndereco(
                        medicos.getEndereco().getCep(),
                        medicos.getEndereco().getLogradouro(),
                        medicos.getEndereco().getBairro(),
                        medicos.getEndereco().getCidade(),
                        medicos.getEndereco().getUf(),
                        medicos.getEndereco().getNumero(),
                        medicos.getEndereco().getComplemento()
                )
        );
    }

    @Override
    public List<Medicos> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Medicos listarById(Long id) {
        Medicos listaMedicos = repository.findById(id).orElse(null);

        if (Objects.isNull(listaMedicos)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do médico não foi encontrado!");
        }

        return listaMedicos;
    }

    @Override
    public Medicos excluir(Long id) {
        Medicos buscarCadastro = listarById(id);

        if (Objects.isNull(buscarCadastro)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do médico não foi encontrado!");
        }

        repository.deleteById(id);

        return buscarCadastro;
    }

    public Medicos editar(Long id, Medicos medicos){
        Medicos medicoAtualizado = listarById(id);

        if (Objects.isNull(medicoAtualizado)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do médico não foi encontrado!");
        }

        if (!Objects.isNull(medicos.getEmail())){
            medicoAtualizado.setEmail(medicos.getEmail());
        }

        if (!Objects.isNull(medicos.getEndereco())){
            medicoAtualizado.setEndereco(new Endereco(null,
                    medicos.getEndereco().getCep(),
                    medicos.getEndereco().getLogradouro(),
                    medicos.getEndereco().getBairro(),
                    medicos.getEndereco().getCidade(),
                    medicos.getEndereco().getUf(),
                    medicos.getEndereco().getNumero(),
                    medicos.getEndereco().getComplemento()
            ));
        }

        return cadastrar(medicoAtualizado);
    }

}
