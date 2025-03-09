package med.voll.api.endereco;

public record EnderecoViaCEP(String cep,
                             String logradouro,
                             String complemento,
                             String bairro,
                             String localidade,
                             String estado,
                             String uf){
    @Override
    public String toString() {
        return "EnderecoViaCEP { " +
                "\ncep: " + cep +
                "\nlogradouro: " + logradouro +
                "\ncomplemento: " + complemento +
                "\nbairro: " + bairro +
                "\nlocalidade: " + localidade +
                "\nestado: " + estado +
                "\nuf: " + uf +
                "\n}";
    }
}
