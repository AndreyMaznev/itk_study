package com.manv;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Animal("Monkey"),
                new Animal("Cat"),
                new Animal("Dog"),
                new Animal("Chicken")
        };

        Filter <Animal> animalNameLenghtFilter = new Filter() {
            @Override
            public Object apply(Object input) {
                return ((Animal) input).getName().length() > 3 ? (Animal) input : null;
            }
        };

        Animal[] animalsWithLengthMoreThanThree = filter(animals, animalNameLenghtFilter);
        System.out.println("-----------------------");
        for (Object animal : animalsWithLengthMoreThanThree) {
            System.out.println(animal);
        }
    }

    public static <T> T[] filter(T[] inputArray, Filter<T> filter) {
        List <T> result = new ArrayList<>();
        for (T input : inputArray) {
            result.add(filter.apply(input));
        }
        return result.toArray(inputArray);
    }
}
