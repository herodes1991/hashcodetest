package com.hashcode.bbq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationFileWriter {

    public static void main(String[] args) throws IOException {

        Pizza pizza;
        String fileName = args[0];
        String output = fileName + ".out";
        FileReader fileReader = new FileReader(fileName);
        PizzaCreator pizzaCreator = new PizzaCreator(fileReader);
        pizza = pizzaCreator.createPizza();
        List<int[]> cookieCutters = originalCutters(pizza);
        List<List<int[]>> shuffle = getReorderedLists(cookieCutters);
        int bestIndex = getBestIndex(pizza, shuffle);
        PizzaCutter pizzaCutter = new PizzaCutter();
        pizzaCutter.setCookieCutters(shuffle.get(bestIndex));
        Pizza resultPizza = pizzaCutter.cutPizza(pizza.clone());
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
        //builder.append(cells);
        System.out.println(builder.toString());
        System.out.println(cells);
        File file = new File(output);
        FileOutputStream out = new FileOutputStream(file);
        out.write(builder.toString().getBytes());
        out.flush();
        out.close();
        System.out.println("done");
    }

    private static int getBestIndex(Pizza pizza, List<List<int[]>> shuffle) {
        int maxCells = 0;
        int bestIndex = -1;

        for (int i = 0; i < shuffle.size(); i++) {

            PizzaCutter pizzaCutter = new PizzaCutter();
            pizzaCutter.setCookieCutters(shuffle.get(i));
            Pizza resultPizza = pizzaCutter.cutPizza(pizza.clone());
            List<Slice> slices = resultPizza.getSlices();
            int cells = 0;
            for (Slice slice : slices) {
                cells += slice.getNumCells();
            }
            System.out.println(cells);
            if (cells > maxCells) {
                maxCells = cells;
                bestIndex = i;
            }
        }
        System.out.println(bestIndex + ":: " + maxCells);
        return bestIndex;
    }

    private static List<List<int[]>> getReorderedLists(List<int[]> cookieCutters) {
        List<List<int[]>> shuffle = new ArrayList<>();

        for (int i = 0; i < cookieCutters.size(); i++) {
            List<int[]> newElement = new ArrayList<>();
            for (int j = i; j < cookieCutters.size(); j++) {
                newElement.add(cookieCutters.get(j));
            }
            for (int j = 0; j < i; j++) {
                newElement.add(cookieCutters.get(j));
            }
            shuffle.add(newElement);
        }
        return shuffle;
    }

    private static List<int[]> originalCutters(Pizza pizza) {
        int minimumNumberOfSlices = (int) Math
                .ceil((double) pizza.getSize() / (double) pizza.getMaximumCellsPerSlice());
        int maximumNumberOfSlices = (int) Math
                .floor((double) pizza.getSize() / (double) pizza.getMinimumIngredientType());
        int minimumSizeOfSlice = pizza.getMinimumIngredientType() * 2;
        int maximumSizeOfSlice = pizza.getMaximumCellsPerSlice();

        CookieCutterCreator cookieCutterCreator = new CookieCutterCreator();
        return cookieCutterCreator.getCookieCutters(minimumSizeOfSlice, maximumSizeOfSlice);
    }

}
