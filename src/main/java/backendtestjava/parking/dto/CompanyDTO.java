package backendtestjava.parking.dto;


import backendtestjava.parking.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;
@Builder
public record CompanyDTO (
        @NotBlank
        UUID id,
        String companyCNPJ,
        Address address,
        String phone,
        Long cars,
        Long bikes
){

}
