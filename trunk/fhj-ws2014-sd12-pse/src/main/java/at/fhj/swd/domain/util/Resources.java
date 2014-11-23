package at.fhj.swd.domain.util;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * <p>
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans.
 * Its a copy from jboss quickstart example project.
 * </p>
 * Example injection on a managed bean field:<br>
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 * 
 * @author Lukas Kranabetter
 */
public class Resources
{
	// use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
	@SuppressWarnings("unused")
	@Produces
	@PersistenceContext
	private EntityManager em;
		
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint)
	{
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	@Produces
	@RequestScoped
	public FacesContext produceFacesContext()
	{
		return FacesContext.getCurrentInstance();
	}	
}