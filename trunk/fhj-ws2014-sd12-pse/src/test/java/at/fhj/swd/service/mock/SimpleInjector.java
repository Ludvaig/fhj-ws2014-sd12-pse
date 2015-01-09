package at.fhj.swd.service.mock;

import java.lang.reflect.Field;

public final class SimpleInjector {
	private SimpleInjector() {
	}
	
	public static void injectProperty(Object classInstance, String propertyName, Object propertyValue) {
		try {
			Field privateField;
			
			privateField = classInstance.getClass().getDeclaredField(propertyName);

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
