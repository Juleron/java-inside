package fr.umlv.javainside.labfive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.stream.Collectors;
import static java.util.function.Predicate.not;

public class Main {
	public static String toJSON(Object person) {
		return Arrays.stream(person.getClass().getMethods())
				//.filter(method -> not(isObjectGetClass(method)))
				.filter(method -> method.getName().startsWith("get"))
				.map(method -> propertyName(method.getName() + " : " + invokeMethod(method, person)))
				.collect(Collectors.joining("\n", "{\n", "}\n"));
		/*return
				"{\n" +
				"  \"firstName\": \"" + person.getFirstName() + "\"\n" +
				"  \"lastName\": \"" + person.getLastName() + "\"\n" +
				"}\n";*/
	}
	
	/*private static boolean isObjectGetClass(Method method) {
		
	}*/

	private static String invokeMethod(Method method, Object o) {
		try {
			return method.invoke(o).toString();
		} catch (IllegalAccessException iae) {
			throw new IllegalStateException(iae);
		} catch(InvocationTargetException ite) {
			var cause = ite.getCause();
			if(cause instanceof RuntimeException) {
				throw (RuntimeException) cause;
			}
			if(cause instanceof Error) {
				throw (Error) cause;
			}
			throw new UndeclaredThrowableException(cause);
		}
	}
	
	/*public static String toJSON(Alien alien) {
		return 
				"{\n" + 
				"  \"planet\": \"" + alien.getPlanet() + "\"\n" + 
				"  \"members\": \"" + alien.getAge() + "\"\n" + 
				"}\n";
	}*/

	private static String propertyName(String name) {
		return Character.toLowerCase(name.charAt(3)) + name.substring(4);
	}

	public static void main(String[] args) {
		var person = new Person("John", "Doe");
		System.out.println(toJSON(person));
		var alien = new Alien("E.T.", 100);
		System.out.println(toJSON(alien));
	}
}