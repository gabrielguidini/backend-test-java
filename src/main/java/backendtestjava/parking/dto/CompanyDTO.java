package backendtestjava.parking.dto;


import backendtestjava.parking.entity.Address;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CompanyDTO (
        @NotBlank
        UUID id,
        String companyCNPJ,
        Address address,
        String phone
){

}
