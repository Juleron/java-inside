package fr.umlv.javainside.labfour;

public class Example1 {
	public static void main() {
		ContinuationScope cs = new ContinuationScope("hello1");
		Continuation suite = new Continuation(cs, () -> {
			//Continuation.yield(cs);
			System.out.println("hello continuation");
		});
		suite.run();
		suite.run();
	}
}
