package fr.umlv.javainside.labtwo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SwitchesTests {
	
	Switches switches;
	
	@Test
	void testIntSwitches() {
		assertEquals(switches.intSwitch(-1), new IllegalArgumentException());
		assertEquals(switches.intSwitch(0),"one");
		assertEquals(switches.intSwitch(1),"two");
		assertEquals(switches.intSwitch(2),"a lot");
	}
}
