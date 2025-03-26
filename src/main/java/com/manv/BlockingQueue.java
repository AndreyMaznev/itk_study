package com.manv;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> threadQueue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.threadQueue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (threadQueue.size() == capacity) {
            System.out.println("Queue now is FULL. Enqueue is impossible. Waiting...");
            wait();
        }
        threadQueue.add(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (threadQueue.isEmpty()) {
            wait();
            System.out.println("Queue is EMPTY. Waiting for the new entities.");
        }
        T item = threadQueue.poll();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return threadQueue.size();
    }
}