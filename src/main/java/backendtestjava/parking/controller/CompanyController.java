package backendtestjava.parking.controller;

import backendtestjava.parking.dto.CompanyDTO;
import backendtestjava.parking.entity.Company;
import backendtestjava.parking.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

//    @PostMapping
//    public ResponseEntity<?> createCompany(@RequestBody CompanyDTO companyDTO, @PathVariable String cep) {
//
//    }
}
