package at.fhj.swd.presentation.viewHelper;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class RedirectionTargetHelper {
	
	static void redirectTo(RedirectionTarget redirectionTarget) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		try {
			ec.redirect(redirectionTarget.getValue() + ".jsf");
		} catch (IOException ex) {
			Logger.getAnonymousLogger().log(java.util.logging.Level.SEVERE, "We are very sorry for this inconvenience! Simply act as if this never happened.");
		}
	}
}
