package backendtestjava.parking.controller;

import backendtestjava.parking.dto.CompanyDTO;
import backendtestjava.parking.entity.Address;
import backendtestjava.parking.entity.Company;
import backendtestjava.parking.service.AddressService;
import backendtestjava.parking.service.CompanyService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/findAllCompanies")
    public List<Company> getAllCompanies() {
        log.info("CompanyService.findCompanies() -> init_process");
        return companyService.findCompanies();
    }

    @PostMapping("/createCompany")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Company> createCompany (@RequestParam @Valid String companyCNPJ,
                                            @RequestParam @Valid String companyCEP,
                                            @RequestParam String companyAddressNumber,
                                            @RequestParam String companyPhone,
                                            @RequestParam Long cars,
                                            @RequestParam Long bikes
                                            ) throws Exception {

        log.info("CompanyController.createCompany() -> init_process");

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

            log.info("CompanyController.createCompany() -> finish_process");

            return companyService.saveCompany(new Company(companyDTO));

        } catch (Exception e) {
            log.error("CompanyController.createCompany() -> ERROR {}", e.getMessage(), e);
            throw new Exception("ERROR couldn't create company -> ",e.getCause());
        }
    }

    @GetMapping("/getCompanyById")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Company getCompanyById(@RequestParam @Valid UUID companyId) throws Exception {
        return companyService.findCompanyById(companyId);
    }

    @PutMapping("/updateCompany")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid CompanyDTO commingCompany) {
        try {
            log.info("CompanyController.updateCompany -> init_process");
            Company company = getCompanyById(commingCompany.id());

            log.info("CompanyController.updateCompany -> finish_process");
            return companyService.saveCompany(company);
        } catch (Exception e) {
            log.error("CompanyController.updateCompany -> ERROR {}, {}",e ,e.getMessage());
            throw new RuntimeException("ERROR -> couldn't update company {} {} ", e.getCause());
        }
    }

    @DeleteMapping("/deleteCompany")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public HttpStatus deleteCompany(@RequestParam UUID companyId){
        return companyService.removeCompany(companyId);
    }
}
