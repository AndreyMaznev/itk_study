package com.manv;


/**
   Предположим, у вас есть пул потоков, и вы хотите реализовать блокирующую очередь для передачи задач между потоками.
   Создайте класс BlockingQueue, который будет обеспечивать безопасное добавление и извлечение элементов между
   производителями и потребителями в контексте пула потоков.
   Класс BlockingQueue должен содержать методы enqueue() для добавления элемента в очередь и dequeue() для извлечения элемента.
   Если очередь пуста, dequeue() должен блокировать вызывающий поток до появления нового элемента.
   Очередь должна иметь фиксированный размер.
   Используйте механизмы wait() и notify() для координации между производителями и потребителями.
   Реализуйте метод size(), который возвращает текущий размер очереди.
 */


public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(5); // Очередь с максимальным размером 5
        IntegerProducer producer = new IntegerProducer(queue);
        IntegerConsumer consumer = new IntegerConsumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();

    }
}