package backendtestjava.parking.service;

import backendtestjava.parking.entity.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CompanyService {
   List<Company> findCompanies();
   ResponseEntity<Company> saveCompany(Company company);
   Company findCompanyById(UUID companyId) throws Exception;
   HttpStatus removeCompany(UUID companyId);
}
