package at.fhj.swd.domain;

/**
 * Global constants.<br>
 * 
 * @author Lukas Kranabetter
 */
public class GlobalConstants
{
	public static final String SYSTEM_PROPERTY_KEY_HASH_ALGORITHM = "hashAlgorithm";
	public static final String SYSTEM_PROPERTY_KEY_HASH_ENCODING = "hashEncoding";
	public static final String SYSTEM_PROPERTY_KEY_HASH_CHARSET = "hashCharset";
	
	public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";
	public static final String DEFAULT_HASH_ENCODING = "Base64";
	public static final String DEFAULT_HASH_CHARSET = "UTF-8";
	
	public static final String MIN_USERNAME_LENGTH = "5";
	public static final String MAX_USERNAME_LENGTH = "25";
	public static final String MIN_PASSWORD_LENGTH = "5";
	public static final String MAX_PASSWORD_LENGTH = "10";
}