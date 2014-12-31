package at.fhj.swd.service.exceptions;

import at.fhj.swd.exceptions.FhjWs2014Sd12PseException;

/** 
 * Denotes the top-level-exception-base-class for all service-layer-exceptions.
 * */
class ServiceLayerException extends FhjWs2014Sd12PseException {

	private static final long serialVersionUID = 1413764263066856623L;

	ServiceLayerException() {
		super();
	}
	
	ServiceLayerException(String msg) {
		super(msg);
	}
	
	ServiceLayerException(String msg, Throwable t) {
		super(msg, t);
	}
	
	// TODO: Implement service-layer specific exception-functionalities here!
}
