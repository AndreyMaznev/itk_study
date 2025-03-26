package com.manv;

public class IntegerConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public IntegerConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer possiblyPoisonPill = queue.dequeue();
                if (possiblyPoisonPill == 666666) {
                    System.out.println("Received poison pill. I'm dying!!! WHYYY did you do this????!?!");
                    break;
                }
                System.out.println("Consuming: " + possiblyPoisonPill);
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
