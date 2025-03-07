package med.voll.api.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Endereco {
    private Integer cep;
    private Integer numero;
    private String complemento;

}
