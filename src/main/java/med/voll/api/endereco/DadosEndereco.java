package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
                            @NotBlank String cep,
                            @NotBlank String logradouro,
                            @NotBlank String bairro,
                            @NotBlank String localidade,
                            @NotBlank String estado,
                            @NotBlank String uf,
                            String numero,
                            String complemento) {
}
