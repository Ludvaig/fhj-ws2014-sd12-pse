package at.fhj.swd.service.mock;

import javax.enterprise.inject.Alternative;

@Alternative
public class LoggerStub extends java.util.logging.Logger {

	protected LoggerStub(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}
	
	public LoggerStub(){
		this(null,null);
	}
	
	@Override
	public void info(String msg) {
	}
	
	@Override
	public void warning(String msg) {
	}
	
	@Override
	public void severe(String msg) {
	}	
}
