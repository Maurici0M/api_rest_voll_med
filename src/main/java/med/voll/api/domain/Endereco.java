package med.voll.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.EnderecoViaCEP;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;

    private String estado;
    private String uf;
    private String numero;
    private String complemento;

    // Construtor padrão
    public Endereco() {}

    @Deprecated
    public Endereco(DadosEndereco endereco) {
        this.cep = endereco.cep();

        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.localidade = endereco.localidade();
        this.estado = endereco.estado();
        this.uf = endereco.uf();

        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }

    public Endereco(EnderecoViaCEP enderecoViaCEP, String numero, String complemento) {
        this.cep = enderecoViaCEP.cep();

        this.logradouro = enderecoViaCEP.logradouro();
        this.bairro = enderecoViaCEP.bairro();
        this.localidade = enderecoViaCEP.localidade();
        this.estado = enderecoViaCEP.estado();
        this.uf = enderecoViaCEP.uf();

        this.numero = numero;
        this.complemento = complemento;

    }

}
