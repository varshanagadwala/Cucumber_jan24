package customexception;

public class InvalidBrowserException extends RuntimeException {

	public InvalidBrowserException() {
		super();
	}

	public InvalidBrowserException(String errorMsg) {
		super(errorMsg);
	}
}
