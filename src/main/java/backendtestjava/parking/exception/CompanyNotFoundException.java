package backendtestjava.parking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class CompanyNotFoundException extends Exception {
    public CompanyNotFoundException(String message) {
        super(message);
        log.error("CompanyNotFoundException -> couldn't find company {} ", message);
    }
}
