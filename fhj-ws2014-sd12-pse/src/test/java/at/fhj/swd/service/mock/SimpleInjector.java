package at.fhj.swd.service.mock;

import java.lang.reflect.Field;

import javax.management.RuntimeErrorException;

public final class SimpleInjector {
	private SimpleInjector() {
	}
	
	public static void injectProperty(Object classInstance, String propertyName, Object propertyValue) {
		try {
			Field privateField;
			
			privateField = classInstance.getClass().getDeclaredField(propertyName);

			if(privateField == null) {
				throw new RuntimeException("The class " + classInstance.getClass().getName() + " has no property named " + propertyName + ".");
			}
			
			privateField.setAccessible(true);

			privateField.set(classInstance, propertyValue);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
