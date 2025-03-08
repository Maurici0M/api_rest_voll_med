package med.voll.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.endereco.DadosEndereco;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer numero;
    private String complemento;

    // Construtor padrão
    public Endereco() {}

    public Endereco(DadosEndereco endereco) {
        this.cep = endereco.cep();
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }

}
