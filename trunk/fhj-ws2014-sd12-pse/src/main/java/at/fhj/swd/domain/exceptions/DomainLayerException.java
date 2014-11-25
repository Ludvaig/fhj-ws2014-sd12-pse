package at.fhj.swd.domain.exceptions;

import at.fhj.swd.exceptions.FhjWs2014Sd12PseException;

/** 
 * Denotes the top-level-exception-base-class for all domain-layer-exceptions.
 * */
class DomainLayerException extends FhjWs2014Sd12PseException {

	private static final long serialVersionUID = -7147962666368460968L;
	
	DomainLayerException() {
		super();
	}
	
	DomainLayerException(String msg) {
		super(msg);
	}
	
	DomainLayerException(String msg, Throwable t) {
		super(msg, t);
	}
	
	// TODO: Implement domain-layer specific exception-functionalities here!
}
