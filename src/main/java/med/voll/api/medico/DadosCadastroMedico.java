package med.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(@NotBlank String nome,
                                  @NotBlank String email,
                                  @NotBlank String crm,
                                  @NotBlank Especialidade especialidade,
                                  DadosEndereco endereco
                                  ) {
}
