package backendtestjava.parking.service;

import backendtestjava.parking.entity.Company;
import backendtestjava.parking.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    //contructor injection > field injection
    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findingCompanies() {
        log.info("CompanyService.findingCompanies() -> init process");
        return companyRepository.findAll();
    }

    public ResponseEntity<Company> createCompany(Company company) {

//        URI uri = uriComponentsBuilder.path("/company").buildAndExpand(companyRepository.
//                findById(company.getCompanyId()).get()).toUri();

        return companyRepository.findById(companyRepository.save(company)
                .getCompanyId()).isPresent() ? ResponseEntity.status(201).build() : ResponseEntity.badRequest().build();
    }

}
