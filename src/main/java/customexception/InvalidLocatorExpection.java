package customexception;

public class InvalidLocatorExpection extends RuntimeException {

	public InvalidLocatorExpection() {
		super();
	}

	public InvalidLocatorExpection(String errorMsg) {
		super(errorMsg);
	}
}
