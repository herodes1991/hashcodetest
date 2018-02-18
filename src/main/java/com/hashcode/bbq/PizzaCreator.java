package com.hashcode.bbq;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PizzaCreator {

    private FileReader fileReader;

    PizzaCreator(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    Pizza createPizza() throws IOException {
        String firstLine = fileReader.readOneLine();
        String[] split = firstLine.split(" ");
        Pizza pizza = new Pizza(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]), Integer.valueOf(split[3]));
        AtomicInteger j = new AtomicInteger(0);
        Stream<String> stream2 = fileReader.readAllLines();
        stream2.forEachOrdered(s -> {
            for (int i = 0; i < s.length(); i++) {
                pizza.addIngredient(String.valueOf(s.charAt(i)), j.get(), i);
            }
            j.incrementAndGet();
        });
        return pizza;
    }
}
