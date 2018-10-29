package fr.umlv.javainside.labfive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.function.Predicate.not;

public class Main {
	
	/*static class Person {
		private final String firstName;
		private final String lastName;

		public Person(String firstName, String lastName) {
			this.firstName = Objects.requireNonNull(firstName);
			this.lastName = Objects.requireNonNull(lastName);
		}

		@JSONProperty
		public String getFirstName() {
			return firstName;
		}
		@JSONProperty
		public String getLastName() {
			return lastName;
		}
	}
	
	static class Alien {
		private final String planet;
		private final int age;

		public Alien(String planet, int age) {
			if (age <= 0) {
				throw new IllegalArgumentException("Too young...");
			}
			this.planet = Objects.requireNonNull(planet);
			this.age = age;
		}
		@JSONProperty
		public String getPlanet() {
			return planet;
		}
		@JSONProperty
		public int getAge() {
			return age;
		}
	}*/
	
	/*public static String toJSON(Object person) {
		return Arrays.stream(person.getClass().getMethods())
				.filter(method -> method.getName().startsWith("get"))
				.map(method -> propertyName(method.getName() + " : " + invokeMethod(method, person).toString()))
				.collect(Collectors.joining("\n", "{\n", "}\n"));
	}*/
	
	public static String toJSON(Object person) {
		return Arrays.stream(person.getClass().getMethods())
				.filter(method -> method.isAnnotationPresent(JSONProperty.class))
				.map(method -> "\"" + propertyName(method.getName() + "\": \"" + invokeMethod(method, person).toString()))
				.collect(Collectors.joining("\"\n", "{\n", "\"\n}"));
	}
	
	private static String propertyName(Method method) {
		method.getAnnotation(JSONProperty.class);
	}

	private static Object invokeMethod(Method method, Object o) {
		try {
			return method.invoke(o);
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

	private static String propertyName(String name) {
		return Character.toLowerCase(name.charAt(3)) + name.substring(4);
	}

	public static void main(String[] args) {
		/*var person = new Person("John", "Doe");
		System.out.println(toJSON(person));
		var alien = new Alien("E.T.", 100);
		System.out.println(toJSON(alien));*/
	}
}