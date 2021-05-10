package bg.infosys.daeu.ws.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class RestExchangeException extends RuntimeException{

	private static final long serialVersionUID = 7444854645755025606L;
	
	private static final Logger logger = LoggerFactory.getLogger(RestExchangeException.class);
    private final HttpStatus httpStatusCode;

    public RestExchangeException(HttpStatus httpStatusCode, String decoded) {
        this.httpStatusCode = httpStatusCode;
        logger.error("Error code {} with {}", httpStatusCode, decoded);
    }

    public HttpStatus getHttpStatusCode(){
        return httpStatusCode;
    }
}
