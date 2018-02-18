package com.hashcode.bbq;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {

        Pizza pizza;
        //String fileName = args[0];
        String fileName = "/Users/Berth/Desktop/bbq/example.in";
        FileReader fileReader = new FileReader(fileName);

        PizzaCreator pizzaCreator = new PizzaCreator(fileReader);
        pizza = pizzaCreator.createPizza();
        PizzaCutter pizzaCutter = new PizzaCutter();
        Pizza resultPizza = pizzaCutter.cutPizza(pizza);
        List<Slice> slices = resultPizza.getSlices();
        int cells = 0;
        System.out.println(slices.size());
        for (Slice slice : slices) {
            cells += slice.getNumCells();
            System.out.println(slice.getRowInit() + " " + slice.getColumnInit() + " " + slice.getRowEnd() + " " + slice.getColumnEnd());
        }
        System.out.println(cells);
    }
}
