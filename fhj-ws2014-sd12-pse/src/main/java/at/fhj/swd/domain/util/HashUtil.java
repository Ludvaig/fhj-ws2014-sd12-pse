package at.fhj.swd.domain.util;

import at.fhj.swd.domain.GlobalConstants;

import org.jboss.security.auth.spi.Util;

/**
 * Util class for hash encoding.<br>
 * 
 * @author Lukas Kranabetter
 */
public class HashUtil 
{   
  public static final String getHashAlgorithmFromSystemProperty()
  {
  	return System.getProperty(GlobalConstants.SYSTEM_PROPERTY_KEY_HASH_ALGORITHM, GlobalConstants.DEFAULT_HASH_ALGORITHM);
  }
  
  public static final String getHashEncodingFromSystemProperty()
  {
  	return System.getProperty(GlobalConstants.SYSTEM_PROPERTY_KEY_HASH_ENCODING, GlobalConstants.DEFAULT_HASH_ENCODING);
  }
  
  public static final String getHashCharsetFromSystemProperty()
  {
  	return System.getProperty(GlobalConstants.SYSTEM_PROPERTY_KEY_HASH_CHARSET, GlobalConstants.DEFAULT_HASH_CHARSET);
  }
 
  public static String getPasswordHash(String username, String password)
  {
  	return Util.createPasswordHash(getHashAlgorithmFromSystemProperty(), 
																		getHashEncodingFromSystemProperty(), 
																		getHashCharsetFromSystemProperty(), 
																		username, 
																		password);
  }
}