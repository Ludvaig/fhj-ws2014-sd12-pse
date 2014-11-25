package at.fhj.swd.presentation.exceptions;

import at.fhj.swd.exceptions.FhjWs2014Sd12PseException;

/** 
 * Denotes the top-level-exception-base-class for all presentation-layer-exceptions.
 * */
public class PresentationLayerException extends FhjWs2014Sd12PseException {

	private static final long serialVersionUID = -7147962666368460968L;
	
	PresentationLayerException() {
		super();
	}
	
	PresentationLayerException(String msg) {
		super(msg);
	}
	
	PresentationLayerException(String msg, Throwable t) {
		super(msg, t);
	}
	
	// TODO: Implement presentation-layer specific exception-functionalities here!
}
