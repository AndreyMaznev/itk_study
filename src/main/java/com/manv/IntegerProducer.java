package com.manv;

import java.util.Random;

public class IntegerProducer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final Random random;

    public IntegerProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.random = new Random();
    }

    @Override
    public void run() {
        int counter = 10;
        while (counter > 0) {
            try {
                    int randomInt = random.nextInt(100);
                    System.out.println("Producing new integer: " + randomInt);
                    queue.enqueue(randomInt);
                    counter--;
                    Thread.sleep(300);
                }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }
        //poison pill
        try {
            System.out.println("Inserting poison pill to kill consumer.");
            queue.enqueue(666666);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
