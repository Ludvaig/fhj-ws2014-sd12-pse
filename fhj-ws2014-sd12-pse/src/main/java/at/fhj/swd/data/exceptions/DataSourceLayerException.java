package at.fhj.swd.data.exceptions;

import at.fhj.swd.exceptions.FhjWs2014Sd12PseException;

/** 
 * Denotes the top-level-exception-base-class for all data-source-layer-exceptions.
 * */
public class DataSourceLayerException extends FhjWs2014Sd12PseException {

	private static final long serialVersionUID = -7147962666368460968L;
	
	DataSourceLayerException() {
		super();
	}
	
	public DataSourceLayerException(String msg) {
		super(msg);
	}
	
	public	DataSourceLayerException(String msg, Throwable t) {
		super(msg, t);
	}
	
	// TODO: Implement data-source-layer specific exception-functionalities here!
}
