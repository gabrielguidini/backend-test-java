package backendtestjava.parking.service;

import backendtestjava.parking.dto.AddressDTO;
import backendtestjava.parking.entity.Address;
import backendtestjava.parking.repository.AddressRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address CEPfounder(String commingCEP) throws Exception {
        log.info("AddressService.CEPfounder() -> init process");
        try {
            URL viaCep = new URL("https://viacep.com.br/ws/"+ commingCEP +"/json/");

            URLConnection connection = viaCep.openConnection();

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            String cep = "";

            StringBuilder jsonCep = new StringBuilder();

            while ((cep = br.readLine()) != null) {

                jsonCep.append(cep);

            }

            cep = commingCEP;

            log.info("AddressService.CEPfounder() -> finished process");

            return new Gson().fromJson(jsonCep.toString(), Address.class);

        } catch (Exception e){
            log.info("AddressService.CEPfounder() -> ERROR {}", e.getMessage());
            throw new Exception("ERROR CAUSE -> {} {}", e);
        }
    }
}
