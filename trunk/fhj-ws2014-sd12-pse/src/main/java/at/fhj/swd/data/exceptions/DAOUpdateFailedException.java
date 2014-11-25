package at.fhj.swd.data.exceptions;

public class DAOUpdateFailedException extends DataSourceLayerException {

	private static final long serialVersionUID = 3677898193496326287L;
	
	public DAOUpdateFailedException(String msg, Throwable t) {
		super(msg, t);
	}
}