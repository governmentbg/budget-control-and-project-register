package bg.infosys.daeu.ws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({MissingInputValuesException.class, RestExchangeException.class})
    public final ResponseEntity<GeneralError> handleException(Exception ex) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof MissingInputValuesException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;

            return handleErrorResponse(status, headers, new GeneralError(Collections.singletonList(ex.getMessage())));
        } else if (ex instanceof RestExchangeException) {
            RestExchangeException ree = (RestExchangeException) ex;
            HttpStatus status = ree.getHttpStatusCode();
            return handleErrorResponse(status, headers, new GeneralError(Collections.singletonList(ex.getMessage())));

        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleErrorResponse(status, headers, null);
        }

    }

    private ResponseEntity<GeneralError> handleErrorResponse(HttpStatus status, HttpHeaders headers, GeneralError content) {
        return new ResponseEntity<>(content, headers, status);
    }

    class GeneralError {

        private final List<String> errors;

        public GeneralError(List<String> errors) {
            this.errors = errors;
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}


