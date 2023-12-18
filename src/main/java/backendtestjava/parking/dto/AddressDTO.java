package backendtestjava.parking.dto;

public record AddressDTO(
        String CEP,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi
) {

}
