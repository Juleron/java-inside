package fr.umlv.javainside.labfive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testToJSONAlien() {
		var alien = new Alien("E.T.", 100);
		assertEquals(Main.toJSON(alien), toJSON(alien));
	}
	
	@Test
	void testToJSONPerson() {
		var person = new Person("John", "Doe");
		assertEquals(Main.toJSON(person), toJSON(person));
	}

	static class Person {
		private final String firstName;
		private final String lastName;

		public Person(String firstName, String lastName) {
			this.firstName = Objects.requireNonNull(firstName);
			this.lastName = Objects.requireNonNull(lastName);
		}

		@JSONProperty(property = "firstName")
		public String getFirstName() {
			return firstName;
		}
		@JSONProperty(property = "lastName")
		public String getLastName() {
			return lastName;
		}
	}
	
	public static String toJSON(Person person) {
		return
				"{\n" +
				"  \"firstName\": \"" + person.getFirstName() + "\"\n" +
				"  \"lastName\": \"" + person.getLastName() + "\"\n" +
				"}\n";
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
		@JSONProperty(property = "planet")
		public String getPlanet() {
			return planet;
		}
		@JSONProperty(property = "members")
		public int getAge() {
			return age;
		}
	}

	public static String toJSON(Alien alien) {
		return 
				"{\n" + 
				"  \"planet\": \"" + alien.getPlanet() + "\"\n" + 
				"  \"members\": \"" + alien.getAge() + "\"\n" + 
				"}\n";
	}

}
