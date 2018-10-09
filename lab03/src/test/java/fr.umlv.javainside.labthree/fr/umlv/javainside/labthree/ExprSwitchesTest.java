package fr.umlv.javainside.labtwo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ExprSwitchesTests {
	/*static class TestData {
		int[] i;
		List<IntFunction> func;
	}*/
	
	@Test
	void testIntSwitches() {
		assertThrows(IllegalArgumentException.class, () -> Switches.intSwitch(-1));
		assertEquals(Switches.intSwitch(0),"zero");
		assertEquals(Switches.intSwitch(1),"one");
		assertEquals(Switches.intSwitch(2),"a lot");
	}
	
	/*@ParameterizedTest
	@MethodSource("stringProvider")
	void testIntSwitches2(IntFunction<String> func) {
		assertThrows(IllegalArgumentException.class, () -> func.apply(-1));
		assertEquals(func.apply(0), "zero");
		assertEquals(func.apply(1), "one");
		assertEquals(func.apply(2), "a lot");
	}*/

	@Test
	void testExprIntSwitches() {
		assertThrows(IllegalArgumentException.class, () -> Switches.intSwitch(-1));
		assertEquals(Switches.intSwitch(0),"zero");
		assertEquals(Switches.intSwitch(1),"one");
		assertEquals(Switches.intSwitch(2),"a lot");
	}
	
	/*static Stream<IntFunction<String>> stringProvider() {
		return Stream.of(Switches::intSwitch, Switches::intSwitch2);
	}*/
	
	/*static Stream<TestData> stringProvider() {
		int[] tabi = new int[4];
		
		for(int i = 0; i < 4; i++) {
			tabi[i] = i-1;
		}
		
		List<IntFunction> funcName = new ArrayList<IntFunction>();
		
		return;
	}*/
	
}
