package fr.umlv.javainside.labtwo;

import java.util.function.IntFunction;
import java.util.stream.Stream;

public class Switches {
	public static String intSwitch(int value) {
		switch(value) {
			case 0: case 3:
				return "zero";
			case 1:
				return "one";
			case 2:
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
	}
	
	public static String intSwitch2(int value) {
		switch(value) {
			case 0: case 3:
				return "zero";
			case 10:
				return "one";
			case 100:
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
	}
	
	
	
}
