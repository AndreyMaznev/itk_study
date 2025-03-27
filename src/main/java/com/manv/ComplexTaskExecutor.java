package com.manv;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private int taskCount;

    public ComplexTaskExecutor(int taskCount) {
        this.taskCount = taskCount;
    }

    public void executeTasks (int numberOfTasks){
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks,
                () -> System.out.println("Cyclic barrier finished successfully with the next task quantity : " + numberOfTasks));
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);

        int tasksLeftCounter = numberOfTasks;
        while (tasksLeftCounter > 0) {
            executorService.submit(new ComplexTask(barrier));
            tasksLeftCounter --;
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                System.out.println("Forced shutdown!");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}