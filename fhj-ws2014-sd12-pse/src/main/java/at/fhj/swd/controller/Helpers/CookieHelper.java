package at.fhj.swd.controller.Helpers;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * helper class for reading and writing cookies
 * 
 * @author Joerg Huber
 */
public class CookieHelper {

	static final String authTokenName = "authToken";

	/**
	 * set a cookie (update if exist otherwise a new one will be created)
	 * */
	public static void setCookie(String name, String value, int expiry) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		Cookie cookie = null;

		Cookie[] userCookies = request.getCookies();
		if (userCookies != null && userCookies.length > 0) {
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(name)) {
					cookie = userCookies[i];
					break;
				}
			}
		}

		if (cookie != null) {
			cookie.setValue(value);
		} else {
			cookie = new Cookie(name, value);
			cookie.setPath(request.getContextPath());
		}

		cookie.setMaxAge(expiry);

		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		response.addCookie(cookie);
	}

	/**
	 * get cookie by name if not found the method returns null
	 * */
	public static Cookie getCookie(String name) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		Cookie cookie = null;

		Cookie[] userCookies = request.getCookies();
		if (userCookies != null && userCookies.length > 0) {
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(name)) {
					cookie = userCookies[i];
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * set a new value to the authentication token
	 * */
	public static void setAuthTokenValue(String token) {
		setCookie(authTokenName, token,36000);
	}

	/**
	 * get the current value of the authentication token
	 * */
	public static String getAuthTokenValue() {
		Cookie cookie = getCookie(authTokenName);
		if (cookie != null)
			return cookie.getValue();
		return null;
	}
}
