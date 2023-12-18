package backendtestjava.parking.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Entity
public class Address {
    @Id @Builder.Default
    private UUID addressId = UUID.randomUUID();
    @NotBlank
    private String CEP;
    @NotBlank
    private String address;
    @NotBlank
    private String number;
}
