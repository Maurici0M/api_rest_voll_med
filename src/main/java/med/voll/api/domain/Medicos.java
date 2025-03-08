package med.voll.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Especialidade;

import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "MEDICOS")
public class Medicos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    private @NotBlank String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @ManyToOne(cascade = CascadeType.ALL) // Relacionamento com endereço
    @JoinColumn(name = "endereco_id") // Define a chave estrangeira
    private Endereco endereco;

    public Medicos(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

}
