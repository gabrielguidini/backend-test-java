package backendtestjava.parking.service;

import backendtestjava.parking.dto.CompanyDTO;
import backendtestjava.parking.entity.Company;
import backendtestjava.parking.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

//    public ResponseEntity<?> createCompany(CompanyDTO company) {
//
//    }

}
