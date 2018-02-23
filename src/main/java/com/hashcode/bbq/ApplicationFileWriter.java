package com.hashcode.bbq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ApplicationFileWriter {

    public static void main(String[] args) throws IOException {

        Pizza pizza;
        // String fileName = args[0];
        String fileName = "/Users/Berth/Desktop/bbq/example.in";

        String output = "/Users/Berth/Desktop/bbq/example.out";
        FileReader fileReader = new FileReader(fileName);

        PizzaCreator pizzaCreator = new PizzaCreator(fileReader);
        pizza = pizzaCreator.createPizza();
        PizzaCutter pizzaCutter = new PizzaCutter();
        Pizza resultPizza = pizzaCutter.cutPizza(pizza);
        List<Slice> slices = resultPizza.getSlices();
        int cells = 0;

        StringBuilder builder = new StringBuilder();
        builder.append(slices.size());
        builder.append("\n");
        for (Slice slice : slices) {
            cells += slice.getNumCells();
            builder.append(slice.getRowInit());
            builder.append(" ");
            builder.append(slice.getColumnInit());
            builder.append(" ");
            builder.append(slice.getRowEnd());
            builder.append(" ");
            builder.append(slice.getColumnEnd());
            builder.append("\n");
        }
        builder.append(cells);

        File file = new File(output);
        FileOutputStream out = new FileOutputStream(file);
        out.write(builder.toString().getBytes());
        out.flush();
        out.close();
        System.out.println("done");
    }
}
