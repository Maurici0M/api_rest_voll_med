package med.voll.api.endereco;

public record EnderecoViaCEP(String cep,
                             String logradouro,
                             String complemento,
                             String bairro,
                             String localidade,
                             String estado,
                             String uf){
}
