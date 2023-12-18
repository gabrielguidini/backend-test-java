package backendtestjava.parking.service;

import backendtestjava.parking.entity.Company;
import backendtestjava.parking.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    //contructor injection > field injection
    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies () {
        return companyRepository.findAll();
    }

    public ResponseEntity<?> createCompany(Company company, UriComponentsBuilder uriComponentsBuilder) {

        URI uri = uriComponentsBuilder.path("/company").buildAndExpand(companyRepository.
                findById(company.getCompanyId()).get()).toUri();

        return companyRepository.findById(companyRepository.save(company)
                .getCompanyId()).isPresent() ?
                ResponseEntity.created(uri).build() : ResponseEntity.badRequest().build();
    }

}
