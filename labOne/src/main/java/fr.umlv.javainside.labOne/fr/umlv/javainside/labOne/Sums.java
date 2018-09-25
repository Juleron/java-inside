package fr.umlv.javainside.labOne;

import java.util.stream.IntStream;

public class Sums {
	public long loopSum(int n) {
		long sum = 0;
		for(int i = 0; i < n; i++) {
			sum = sum + i;
		}
		return sum;
	}
	
	public long streamSum(int n) {
		return IntStream.range(0, n).sum();
	}
}
