package med.voll.api.service;

import med.voll.api.endereco.EnderecoViaCEP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Service
public class APIViaCepServiceImpl {

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(APIViaCepServiceImpl.class);

    @Autowired
    public APIViaCepServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EnderecoViaCEP buscarEnderecoByApi(String cep){
        if (Objects.isNull(cep) || cep.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O CEP digitado não foi encontrado na API ViaCEP!");
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        EnderecoViaCEP consultaApi = restTemplate.getForObject(url, EnderecoViaCEP.class);

        // Depuração para verificar os dados retornados
        //logger.info("Endereço retornado: {}", consultaApi);

        // Verifique se a api retorna erro e emite a mensagem not_found

        if (Objects.isNull(consultaApi)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado para o CEP fornecido.");
        }

        return consultaApi;
    }

}
