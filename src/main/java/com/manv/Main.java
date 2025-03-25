package com.manv;

import java.util.HashMap;
import java.util.Map;

/**
 Напишите метод, который получает на вход массив элементов и возвращает Map ключи в котором - элементы,
 а значения - сколько раз встретился этот элемент
 */

public class Main {
    public static void main(String[] args) {
        String [] stringsArray = {"Hey", "lets", "count", "how", "many", "times", "kangaroo", "can", "jump",
                "in", "our", "code:", "jump", "jump", "jump"};
        Map<String, Integer> map = calculateCount(stringsArray);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Entry: " + entry.getKey() + " counted: " + entry.getValue());
        }
    }

    public static <K> Map<K, Integer> calculateCount (K [] inputArray){
        Map<K , Integer> resultMap = new HashMap<>();
        for (K key : inputArray) {
            resultMap.merge(key, 1, Integer::sum);
        }
        return resultMap;
    }
}