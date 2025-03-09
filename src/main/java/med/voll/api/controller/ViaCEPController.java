package med.voll.api.controller;

import med.voll.api.endereco.EnderecoViaCEP;
import med.voll.api.service.APIViaCepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("viacep")
public class ViaCEPController {

    @Autowired
    private final APIViaCepServiceImpl serviceViaCep;

    public ViaCEPController(APIViaCepServiceImpl serviceViaCep) {
        this.serviceViaCep = serviceViaCep;
    }

    //TESTE API VIACEP
    @GetMapping("/{cep}")
    public String testeApi(@PathVariable String cep){
        EnderecoViaCEP enderecoViaCEP = serviceViaCep.buscarEnderecoByApi(cep);

        return enderecoViaCEP.toString();
    }


}
