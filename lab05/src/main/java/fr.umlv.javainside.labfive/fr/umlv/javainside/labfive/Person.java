package fr.umlv.javainside.labfive;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {
	private final String firstName;
	private final String lastName;

	public Person(String firstName, String lastName) {
		this.firstName = Objects.requireNonNull(firstName);
		this.lastName = Objects.requireNonNull(lastName);
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public static String toJSON(Object person) {
		return Arrays.stream(person.getClass().getMethods())
			.filter(method -> method.getName().startsWith("get"))
			.collect(Collectors.toList()).toString(); 
		/*return
				"{\n" +
				"  \"firstName\": \"" + person.getFirstName() + "\"\n" +
				"  \"lastName\": \"" + person.getLastName() + "\"\n" +
				"}\n";*/
	}
}