package backendtestjava.parking.controller;

import backendtestjava.parking.dto.CompanyDTO;
import backendtestjava.parking.entity.Address;
import backendtestjava.parking.entity.Company;
import backendtestjava.parking.service.AddressService;
import backendtestjava.parking.service.CompanyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {
    private final CompanyService companyService;
    private final AddressService addressService;

    @Autowired
    public CompanyController(CompanyService companyService, AddressService addressService) {
        this.companyService = companyService;
        this.addressService = addressService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        log.info("CompanyService.findingCompanies() -> finished process");
        return companyService.findingCompanies();
    }

    @PostMapping
    public ResponseEntity<Company> createCompany (@RequestParam @Valid String companyCNPJ,
                                            @RequestParam @Valid String companyCEP,
                                            @RequestParam String companyAddressNumber,
                                            @RequestParam String companyPhone,
                                            @RequestParam Long cars,
                                            @RequestParam Long bikes
                                            ) throws Exception {

        log.info("CompanyController.createCompany() -> init process");

        Address address = addressService.CEPfounder(companyCEP);
        address.setComplemento(companyAddressNumber);
        address.setCEP(companyCEP);

        try {
            CompanyDTO companyDTO = CompanyDTO.builder()
                    .id(UUID.randomUUID())
                    .companyCNPJ(companyCNPJ)
                    .address(address)
                    .phone(companyPhone)
                    .cars(cars)
                    .bikes(bikes)
                    .build();

            log.info("CompanyController.createCompany() -> finished process");

            return companyService.createCompany(new Company(companyDTO));

        } catch (Exception e) {
            log.error("CompanyController.createCompany() -> ERROR {}", e.getMessage(), e);
            throw new Exception("ERROR couldn't create company -> ",e.getCause());
        }
    }
}
