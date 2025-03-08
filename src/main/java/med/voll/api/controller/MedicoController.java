package med.voll.api.controller;

import med.voll.api.domain.Medicos;
import med.voll.api.service.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private final MedicoServiceImpl service;

    public MedicoController(MedicoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public Medicos cadastrar (@RequestBody Medicos dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public List<Medicos> listarTodos (){
        return service.listarTodos();
    }

    @GetMapping("{id}")
    public Medicos listarById(@PathVariable Long id){
        return service.listarById(id);
    }

    @DeleteMapping("{id}")
    @Transactional
    public Medicos deletarById(@PathVariable Long id) {
        return service.excluir(id);
    }

    @PatchMapping("{id}")
    @Transactional
    public Medicos editarById(@PathVariable Long id, @RequestBody Medicos medicos){
        return service.editar(id, medicos);
    }

}
