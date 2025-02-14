package ru.nsu.berezin.primes;

import java.util.ArrayList;
import java.util.List;
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

    List<Integer> primes = new ArrayList<>();

    @Setup
    public void setup() {
        for (int i = 0; i < 10000000; i++) {
            if (AllPrimes.isPrime(i)) {
                primes.add(i);
            }
        }
    }

    @Benchmark
    public void sequential(Blackhole blackhole) {
        blackhole.consume(AllPrimes.sequential(primes));
    }

    @Benchmark
    public void thread1(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(AllPrimes.threads(primes, 1));
    }

    @Benchmark
    public void thread2(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(AllPrimes.threads(primes, 2));
    }

    @Benchmark
    public void thread3(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(AllPrimes.threads(primes, 3));
    }

    @Benchmark
    public void thread4(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(AllPrimes.threads(primes, 4));
    }

    @Benchmark
    public void thread100(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(AllPrimes.threads(primes, 100));
    }

    @Benchmark
    public void parallelStream(Blackhole blackhole) {
        blackhole.consume(AllPrimes.parallelStream(primes));
    }
}
