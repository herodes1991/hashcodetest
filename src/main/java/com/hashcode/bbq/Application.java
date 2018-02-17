package com.hashcode.bbq;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        Pizza pizza;
        String fileName = "/Users/Berth/Desktop/bbq/example.in";
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            String first = stream.findFirst().get();
            String[] split = first.split(" ");
            pizza = new Pizza(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]), Integer.valueOf(split[3]));
            AtomicInteger j = new AtomicInteger(0);
            Stream<String> stream2 = Files.lines(Paths.get(fileName));
            stream2.skip(1).forEachOrdered(s -> {
                for (int i = 0; i < s.length(); i++) {
                    pizza.addIngredient(String.valueOf(s.charAt(i)), j.get(), i);
                }
                j.incrementAndGet();
            });
            int minimumNumberOfSlices = 9;
            int maximumNumberOfSlices = 18;
            int minimumSizeOfSlice = 2;
            int maximumSizeOfSlice = 5;
            //calcular cookie cutter
            Ingredient[][] ingredients = pizza.getIngredients();
            for (int i = 0; i < pizza.getRows(); i++) {
                for (int k = 0; k < pizza.getColumns(); k++) {
                    if (!ingredients[i][k].isOccupied()) {
                        Slice slice = createSlice(pizza, i, k, minimumSizeOfSlice, maximumSizeOfSlice);
                        if (slice != null) {
                            pizza.addSlice(slice);
                        }
                    }
                }
            }
            List<Slice> slices = pizza.getSlices();
            System.out.println(slices.size());
            for (Slice slice : slices) {
                System.out.println(slice.getRowInit() + " " + slice.getColumnInit() + " " + slice.getRowEnd() + " " + slice.getColumnEnd());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Slice createSlice(Pizza pizza, int rowNum, int columnNum, int minimumSizeOfSlice, int maximumSizeOfSlice) {
        int[][] cookieCutter = {{1, 2}, {2, 1}, {1, 3}, {3, 1}, {1, 4}, {2, 2}, {4, 1}, {1, 5}, {5, 1}, {1, 6}, {2, 3}, {3, 2}, {6, 1}};
        for (int i = 0; i < cookieCutter.length; i++) {
            if (canCut(pizza, cookieCutter[i], rowNum, columnNum)) {
                return new Slice(columnNum, rowNum, columnNum + cookieCutter[i][1] - 1, rowNum + cookieCutter[i][0] - 1);
            }
        }
        return null;
    }

    private static boolean canCut(Pizza pizza, int[] cookieCutter, int rowNum, int columnNum) {
        if (rowNum + cookieCutter[0] > pizza.getRows() || columnNum + cookieCutter[1] > pizza.getColumns()) {
            return false;
        }
        int mushrooms = 0;
        int tomatoes = 0;
        Ingredient[][] ingredients = pizza.getIngredients();
        for (int i = rowNum; i < rowNum + cookieCutter[0]; i++) {
            for (int j = columnNum; j < columnNum + cookieCutter[1]; j++) {
                if (ingredients[i][j].isOccupied()) {
                    return false;
                }
                if (ingredients[i][j].getIngredient() == Ingredient.IngredientEnum.M) {
                    mushrooms++;
                } else {
                    tomatoes++;
                }
            }
        }
        return Math.min(mushrooms, tomatoes) >= pizza.getMinimumIngredientType();
    }

}
