package fr.umlv.javainside.labOne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumsTests {
	
	@Test
	void testLoopSum() {
		Sums sum = new Sums();
		long loopSum = sum.loopSum(5);
		Assertions.assertEquals(loopSum, 10);
	}
	
	@Test
	void testStreamSum() {
		Sums sum = new Sums();
		long streamSum = sum.streamSum(5);
		Assertions.assertEquals(streamSum, 10);
	}
}
