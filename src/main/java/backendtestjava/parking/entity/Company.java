package backendtestjava.parking.entity;

import backendtestjava.parking.dto.CompanyDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
    @Id @Builder.Default
    private UUID companyId = UUID.randomUUID();
    @NotBlank
    private String companyCNPJ;
    @NotBlank @OneToOne(cascade = CascadeType.ALL)
    private Address companyCEP;
    @NotBlank
    private String companyPhone;
    @NotNull
    private Long bikeOccupation;
    @NotNull
    private Long carOccupation;

    public Company(CompanyDTO companyDTO){
        this.companyId = companyDTO.id();
        this.companyCNPJ = companyDTO.companyCNPJ();
        this.companyCEP = companyDTO.address();
        this.companyPhone = companyDTO.phone();
        this.bikeOccupation = companyDTO.bikes();
        this.carOccupation = companyDTO.cars();
    }
}
