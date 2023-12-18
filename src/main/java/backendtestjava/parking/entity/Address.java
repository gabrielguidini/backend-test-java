package backendtestjava.parking.entity;


import backendtestjava.parking.dto.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id @Builder.Default
    private UUID addressId = UUID.randomUUID();

    // I'm using portuguese in this section because it's easier to locate myself throughout the ViaCep API payload
    @NotBlank
    private String CEP;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String localidade;

    @NotBlank
    private String uf;

    @NotBlank
    private String ibge;

    @NotBlank
    private String gia;

    @NotBlank
    private String ddd;

    @NotBlank
    private String siafi;

    public Address(AddressDTO addressDTO) {
        this.CEP = addressDTO.CEP();
        this.logradouro = addressDTO.logradouro();
        this.complemento = addressDTO.complemento();
        this.bairro = addressDTO.bairro();
        this.localidade = addressDTO.localidade();
        this.uf = addressDTO.uf();
        this.ddd = addressDTO.ddd();
    }
}
