package com.hashcode.bbq;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Pizza pizza = new Pizza(6, 7, 1, 5);
        Ingredient[][] ingredients = {
                {new Ingredient("T"), new Ingredient("M"), new Ingredient("M"), new Ingredient("M"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T")},
                {new Ingredient("M"), new Ingredient("M"), new Ingredient("M"), new Ingredient("M"), new Ingredient("T"), new Ingredient("M"), new Ingredient("M")},
                {new Ingredient("T"), new Ingredient("T"), new Ingredient("M"), new Ingredient("T"), new Ingredient("T"), new Ingredient("M"), new Ingredient("T")},
                {new Ingredient("T"), new Ingredient("M"), new Ingredient("M"), new Ingredient("T"), new Ingredient("M"), new Ingredient("M"), new Ingredient("M")},
                {new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("M")},
                {new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("T"), new Ingredient("M")}
        };
        pizza.setIngredients(
                ingredients
        );
        int minimumNumberOfSlices = 9;
        int maximumNumberOfSlices = 18;
        int minimumSizeOfSlice = 2;
        int maximumSizeOfSlice = 5;
        Ingredient[][] ingredients1 = pizza.getIngredients();
        for (int i = 0; i < pizza.getRows(); i++) {
            for (int j = 0; j < pizza.getColumns(); j++) {
                if (!ingredients[i][j].isOccupied()) {
                    Slice slice = createSlice(pizza, i, j, minimumSizeOfSlice, maximumSizeOfSlice);
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
    }

    private static Slice createSlice(Pizza pizza, int rowNum, int columnNum, int minimumSizeOfSlice, int maximumSizeOfSlice) {
        int[][] cuttyCutter = {{1, 2}, {2, 1}, {1, 3}, {3, 1}, {1, 4}, {2, 2}, {4, 1}, {1, 5}, {5, 1}};
        for (int i = 0; i < cuttyCutter.length; i++) {
            if (canCut(pizza, cuttyCutter[i], rowNum, columnNum)) {
                return new Slice(columnNum, rowNum, columnNum + cuttyCutter[i][1] - 1, rowNum + cuttyCutter[i][0] - 1);
            }
        }
        return null;
    }

    private static boolean canCut(Pizza pizza, int[] cuttyCutter, int rowNum, int columnNum) {
        if (rowNum + cuttyCutter[0] > pizza.getRows() || columnNum + cuttyCutter[1] > pizza.getColumns()) {
            return false;
        }
        int mushrooms = 0;
        int tomatoes = 0;
        Ingredient[][] ingredients = pizza.getIngredients();
        for (int i = rowNum; i < rowNum + cuttyCutter[0]; i++) {
            for (int j = columnNum; j < columnNum + cuttyCutter[1]; j++) {
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
