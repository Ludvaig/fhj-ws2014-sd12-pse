package at.fhj.swd.domain.exceptions;

public class InsufficientUserPriviledgesException extends DomainLayerException {

	private static final long serialVersionUID = 7453489558735172579L;

	public InsufficientUserPriviledgesException(String msg) {
		super(msg);
	}
}
