package com.manv;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private final Random rand = new Random();
    private final int randomNumber;
    private final CyclicBarrier barrier;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
        this.randomNumber = rand.nextInt(1,50);
    }

    public void execute () {
        try {
            System.out.println("Task execution with random number: "
                    + randomNumber
                    + " has been started in the pool-thread : "
                    + Thread.currentThread().getName());
            Thread.sleep(rand.nextInt(400, 4000));
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}
