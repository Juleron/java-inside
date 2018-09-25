package fr.umlv.javainside.labOne;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class TestBenchMark {
	
	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestBenchMark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	@Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureLoopSum() throws InterruptedException {
		Sums sum = new Sums();
		sum.loopSum(5);
    }
	
	@Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
	public void measureStreamSum() {
		Sums sum = new Sums();
		sum.streamSum(5);
	}
}
