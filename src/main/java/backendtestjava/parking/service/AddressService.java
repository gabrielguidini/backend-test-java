package backendtestjava.parking.service;

import backendtestjava.parking.entity.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    Address CEPfounder(String commingCep) throws Exception;

}
