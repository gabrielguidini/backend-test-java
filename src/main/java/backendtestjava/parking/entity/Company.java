package backendtestjava.parking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank @OneToOne
    @PrimaryKeyJoinColumn
    private Address companyAddress;
    @NotBlank
    private String companyPhone;
    private Long bikeOccupation;
    private Long carOccupation;
}
