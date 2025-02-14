package ru.nsu.berezin.primes;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class PrimesBenchmark {

    static int smallArray[] = {2, 3, 5, 7, 11, 13, 4};

    @Benchmark
    public static void smallSequential(Blackhole blackhole) {
        Iterable<Integer> array = () -> Arrays.stream(smallArray).boxed().iterator();
        blackhole.consume(AllPrimes.sequential(array));
    }

    @Benchmark
    public static void smallThread(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(AllPrimes.threads(smallArray, 4));
    }

    @Benchmark
    public static void smallParallelStream(Blackhole blackhole) {
        Collection<Integer> array = Arrays.stream(smallArray).boxed().toList();
        blackhole.consume(AllPrimes.parallelStream(array));
    }
}
