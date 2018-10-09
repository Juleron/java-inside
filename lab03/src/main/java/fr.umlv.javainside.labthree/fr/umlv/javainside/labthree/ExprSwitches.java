package fr.umlv.javainside.labthree;

import java.util.function.IntFunction;
import java.util.stream.Stream;

public class ExprSwitches {

	enum Level {
		DEBUG,
		ERROR,
		WARNING,
		INFO
	}

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
			case "foo": case "viva zorg":
				return "zero";
			case "bar":
				return "one";
			case "baz":
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
	}

	public static String enumSwitch(Level value) {
		switch(value) {
			case DEBUG: case ERROR:
				return "zero";
			case WARNING:
				return "one";
			case INFO:
				return "a lot";
			default:
				throw new IllegalArgumentException();
		}
	}

	public static String exprIntSwitch(int value) {
		return switch(value) {
			case 0, 3:
				break "zero";
			case 1:
				break "one";
			case 2:
				break "a lot";
			default:
				throw new IllegalArgumentException();
		};
	}
	
	public static String exprIntSwitch2(int value) {
		return switch(value) {
			case 0, 3 -> "zero";
			case 1 -> "one";
			case 2 -> "a lot";
			default -> throw new IllegalArgumentException();
		};
	}

	public static String exprStringSwitch(String value) {
		return switch(value) {
			case "foo", "viva zorg" -> "zero";
			case "bar" -> "one";
			case "baz" -> "a lot";
			default -> throw new IllegalArgumentException();
		};
	}

	public static String exprEnumSwitch(Level value) {
		return switch(value) {
			case DEBUG, ERROR -> "zero";
			case WARNING -> "one";
			case INFO -> "a lot";
			default -> throw new IllegalArgumentException();
		};
	}
}