package at.fhj.swd.exceptions;

/** 
 * Denotes the top-level-exception-base-class for the SWD12-PSE-web-project.
 * */
public class FhjWs2014Sd12PseException extends RuntimeException {

	private static final long serialVersionUID = -5338057169666191904L;
	
	public FhjWs2014Sd12PseException() {
		super();
	}
	
	public FhjWs2014Sd12PseException(String msg) {
		super(msg);
	}
	
	public FhjWs2014Sd12PseException(String msg, Throwable t) {
		super(msg, t);
	}
	
	// TODO: Implement basic exception-functionalities here [e.g.: getCause(), getMessage() ...]!
}
