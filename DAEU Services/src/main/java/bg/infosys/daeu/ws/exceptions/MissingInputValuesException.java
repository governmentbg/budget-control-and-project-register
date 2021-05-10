package bg.infosys.daeu.ws.exceptions;

public class MissingInputValuesException extends RuntimeException {

	private static final long serialVersionUID = -1633760347695885657L;

	public MissingInputValuesException(String message){
        super(message);
    }
}
