package fr.umlv.javainside.labtwo;

public class Switches {
	public String intSwitch(int value) {
		switch(value) {
			case 0:
				return "zero";
			case 1:
				return "one";
			case 2:
				return "a lot";
			default:
				throw new IllegalArgumentException;
		}
	}
}
