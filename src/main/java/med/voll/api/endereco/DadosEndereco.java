package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
                            @NotBlank Integer cep,
                            @NotBlank String logradouro,
                            @NotBlank String bairro,
                            @NotBlank String cidade,
                            @NotBlank String uf,
                            Integer numero,
                            String complemento) {
}
