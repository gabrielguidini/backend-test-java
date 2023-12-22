package backendtestjava.parking.service.implementation;

import backendtestjava.parking.entity.Address;
import backendtestjava.parking.repository.AddressRepository;
import backendtestjava.parking.service.AddressService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address CEPfounder(String commingCEP) throws Exception {
        log.info("AddressService.CEPfounder() -> init process");
        try {
            URL viaCep = new URL("https://viacep.com.br/ws/"+ commingCEP +"/json/");

            URLConnection connection = viaCep.openConnection();

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String cep = "";

            StringBuilder jsonCep = new StringBuilder();

            while ((cep = br.readLine()) != null) {

                jsonCep.append(cep);

            }

            Address address = new Gson().fromJson(jsonCep.toString(), Address.class);

            log.info("AddressService.CEPfounder() -> saving process");

            addressRepository.save(address);

            log.info("AddressService.CEPfounder() -> finished process");

            return address;

        } catch (Exception e){
            log.info("AddressService.CEPfounder() -> ERROR {}", e.getMessage());
            throw new Exception("ERROR CAUSE -> {} {}", e.getCause());
        }
    }
}
