package med.voll.api.service;

import med.voll.api.domain.Endereco;
import med.voll.api.domain.Medicos;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.EnderecoViaCEP;
import med.voll.api.interfaces.IMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.repository.MedicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;


@Service
public class MedicoServiceImpl implements IMedico {

    private static final Logger logger = LoggerFactory.getLogger(MedicoServiceImpl.class);

    @Autowired
    private final MedicoRepository repository;

    @Autowired
    private APIViaCepServiceImpl viaCepService;

    public MedicoServiceImpl(MedicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medicos cadastrar(Medicos dados) {

        if (!Objects.isNull(dados.getEndereco()) && !Objects.isNull(dados.getEndereco().getCep())) {
            // Buscar dados do endereço a partir da API ViaCEP
            EnderecoViaCEP enderecoViaCEP = viaCepService.buscarEnderecoByApi(dados.getEndereco().getCep());

            // Preencher os outros campos do endereço
            Endereco enderecoCompleto = new Endereco(
                    enderecoViaCEP,
                    dados.getEndereco().getNumero(),
                    dados.getEndereco().getComplemento()
            );

            dados.setEndereco(enderecoCompleto);
        }

        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP do endereço é obrigatório!");
        }

        return repository.save(dados);
    }

    public DadosCadastroMedico cadastrarFormatado(Medicos medicos) {
        return new DadosCadastroMedico(
                medicos.getNome(),
                medicos.getEmail(),
                medicos.getCrm(),
                medicos.getEspecialidade(),

                new DadosEndereco(
                        medicos.getEndereco().getCep(),
                        medicos.getEndereco().getLogradouro(),
                        medicos.getEndereco().getBairro(),
                        medicos.getEndereco().getLocalidade(),
                        medicos.getEndereco().getEstado(),
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do médico não foi encontrado, verifique e tente novamente!");
        }

        cadastrarFormatado(listaMedicos);

        return listaMedicos;
    }

    @Override
    public Medicos excluir(Long id) {
        Medicos buscarCadastro = listarById(id);

        if (Objects.isNull(buscarCadastro)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do médico não foi encontrado, por isso, não será possível excluir o cadastro!");
        }

        repository.deleteById(id);

        return buscarCadastro;
    }

    public Medicos editar(Long id, Medicos medicos){
        Medicos medicoAtualizado = listarById(id);

        if (Objects.isNull(medicoAtualizado)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do médico não foi encontrado, e não será possível editar o cadastro!");
        }

        if (!Objects.isNull(medicos.getEmail())){
            medicoAtualizado.setEmail(medicos.getEmail());
        }

        if (!Objects.isNull(medicos.getEndereco()) && !Objects.isNull(medicos.getEndereco().getCep())){
            EnderecoViaCEP enderecoViaCEP = viaCepService.buscarEnderecoByApi(medicos.getEndereco().getCep());

            medicoAtualizado.setEndereco(new Endereco(
                    enderecoViaCEP,
                    medicos.getEndereco().getNumero(),
                    medicos.getEndereco().getComplemento()
            ));
        }

        return cadastrar(medicoAtualizado);
    }

}
