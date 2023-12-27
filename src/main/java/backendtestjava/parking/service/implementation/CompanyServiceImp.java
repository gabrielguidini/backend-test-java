package backendtestjava.parking.service.implementation;

import backendtestjava.parking.entity.Company;
import backendtestjava.parking.exception.CompanyNotFoundException;
import backendtestjava.parking.repository.CompanyRepository;
import backendtestjava.parking.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CompanyServiceImp implements CompanyService {
    private static final String COMPANY_NOT_FOUND_MESSAGE = "Company Not Found";
    private final CompanyRepository companyRepository;

    //contructor injection > field injection
    @Autowired
    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findCompanies() {
        log.info("CompanyService.findCompanies() -> finish_process");
        return companyRepository.findAll();
    }

    @Override
    public ResponseEntity<Company> saveCompany(Company company) {

        return companyRepository.findById(companyRepository.save(company)
                .getCompanyId()).isPresent() ? ResponseEntity.status(201).build() : ResponseEntity.badRequest().build();
    }

    @Override
    public Company findCompanyById(UUID companyId) throws Exception {

        log.info("CompanyServiceImp.findCompanyById() -> init_process");

        Optional<Company> foundCompany = this.companyRepository.findById(companyId);

        return foundCompany.orElseThrow(()-> new CompanyNotFoundException(COMPANY_NOT_FOUND_MESSAGE));

    }

    @Override
    public HttpStatus removeCompany(UUID companyId) {
        log.info("CompanyServiceImp.removeCompany() -> init_process");

        Optional<Company> company = companyRepository.findById(companyId);

        companyRepository.delete(company.get());

        log.info("CompanyServiceImp.removeCompany() -> finish_process");

        return HttpStatus.ACCEPTED;
    }

}
