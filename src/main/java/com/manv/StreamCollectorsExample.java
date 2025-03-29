package com.manv;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        System.out.println("2. Группировка заказов по продуктам:");
        Map <String, List<Order>> groupedOrdersByProducts = orders.stream()
                .collect(Collectors.groupingBy (o -> o.getProduct()));
        for (Map.Entry<String, List<Order>> entry : groupedOrdersByProducts.entrySet()) {
            System.out.println("Продукт: " + entry.getKey());
            for (Order order : entry.getValue()) {
                System.out.println(order);
            }
            System.out.println("-----------------------------------");
        }
        System.out.println("Готово.");
        System.out.println("");


        System.out.println("3. Общая стоимость всех заказов для каждого продукта:");
        Map <String, Double> totalOrdersCostForEachProduct = orders.stream()
                .collect(Collectors.groupingBy(o -> o.getProduct(), Collectors.summingDouble(o -> o.getCost())));

        for (Map.Entry <String, Double> entry : totalOrdersCostForEachProduct.entrySet()) {
            System.out.println("Продукт: " + entry.getKey());
            System.out.println("Общая стоимость всех заказов: " + entry.getValue());
        }
        System.out.println("Готово.");
        System.out.println("");

        System.out.println("4. Сортировка продуктов по убыванию общей стоимости:");
        List <Order> sortedInDescendingOrderOfTotalPrice = orders.stream()
                .sorted(new Comparator<Order>() { //todo ->
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getCost() < o2.getCost() ? -1 : 1;
                    }
                })
                .toList();
        for (Order order : sortedInDescendingOrderOfTotalPrice) {
            System.out.println(order);
        }
        System.out.println("Готово.");
        System.out.println("");

        System.out.println("5. Выбираем три самых дорогих продукта:");
        List <Order> threeOrdersWithProductMaxPrice = orders.stream()
                .collect (Collectors.groupingBy(Order::getProduct))
                .values()
                .stream()
                .map(ordersList -> ordersList.stream()
                        .max(Comparator.comparingDouble(Order::getCost))
                        .orElseThrow())
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .toList();

        for (Order order : threeOrdersWithProductMaxPrice) {
            System.out.println("Заказ: " + order);
        }
        System.out.println("Готово.");
        System.out.println("");


        //Самые дорогие уникальные продукты ? или вообще любые продукты?
        System.out.println("6. Выбираем три самых дорогих продукта:");
        orders.stream()
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            list.forEach(order -> System.out.println("Наименование продукта : " + order.getProduct()));
                            double cost = list.stream()
                                    .mapToDouble(Order::getCost)
                                    .sum();
                            System.out.println("Общая стоимость : " + cost);
                            return cost;
                        }
                ));

        System.out.println("Готово.");
        System.out.println("");
    }
}