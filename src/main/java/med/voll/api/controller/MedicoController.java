package med.voll.api.controller;

import med.voll.api.domain.Medicos;
import med.voll.api.endereco.EnderecoViaCEP;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.service.APIViaCepServiceImpl;
import med.voll.api.service.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private final MedicoServiceImpl serviceMedico;

    @Autowired
    private final APIViaCepServiceImpl serviceViaCep;

    public MedicoController(MedicoServiceImpl serviceMedico, APIViaCepServiceImpl serviceViaCep) {
        this.serviceMedico = serviceMedico;
        this.serviceViaCep = serviceViaCep;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Medicos> cadastrar (@RequestBody Medicos dados) {
        Medicos salvar = serviceMedico.cadastrar(dados);

        return ResponseEntity.ok(salvar);
    }

    @GetMapping
    public ResponseEntity<List<DadosCadastroMedico>> listarTodos (){
        List<Medicos> listaDeMedicos = serviceMedico.listarTodos();

        List<DadosCadastroMedico> listaFormatada = listaDeMedicos
                .stream()
                .map(medicos -> serviceMedico.cadastrarFormatado(medicos))
                .toList();

        return ResponseEntity.ok(listaFormatada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCadastroMedico> listarById(@PathVariable Long id){
        Medicos verificarCadastro = serviceMedico.listarById(id);

        DadosCadastroMedico cadastroFormatado = serviceMedico.cadastrarFormatado(verificarCadastro);

        return ResponseEntity.ok(cadastroFormatado);
    }

    @GetMapping("/ddd/{ddd}")
    public ResponseEntity<List<Medicos>> listarByDdd(@PathVariable String ddd) {
        List<Medicos> listarPorDdd = serviceMedico.listarByDdd(ddd);

        return ResponseEntity.ok(listarPorDdd);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Medicos> deletarById(@PathVariable Long id) {
        Medicos deletarCadastro = serviceMedico.excluir(id);

        return ResponseEntity.ok(deletarCadastro);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Medicos> editarById(@PathVariable Long id, @RequestBody Medicos medicos){
        Medicos editarCadastro = serviceMedico.editar(id, medicos);

        return ResponseEntity.ok(editarCadastro);
    }

}
