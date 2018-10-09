package fr.umlv.javainside.labthree;

import java.util.function.IntFunction;
import java.util.stream.Stream;

public class ExprSwitches {
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

	public static String stringSwitch(String value) {
		switch(value) {
			case "foo": case "bar":
				return "zero";
			case "baz":
				return "one";
			case "viva zorg":
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
	}

	public static String exprIntSwitch(int value) {
		return switch(value) {
			case 0,3 -> "zero";
			case 1 -> "one";
			case 2 -> "a lot";
			default -> throw new IllegalArgumentException();
		};
	}
	
	/*public static String exprIntSwitch2(int value) {
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

	public static String exprStringSwitch(String value) {
		switch(value) {
			case "foo": case "bar":
				return "zero";
			case "baz":
				return "one";
			case "viva zorg":
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
	}*/
}