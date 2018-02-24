package com.hashcode.bbq;

import java.util.List;

public class PizzaCutter {

    List<int[]> cookieCutters;
    
    
    
    public List<int[]> getCookieCutters() {
        return cookieCutters;
    }

    public void setCookieCutters(List<int[]> cookieCutters) {
        this.cookieCutters = cookieCutters;
    }

    Pizza cutPizza(Pizza pizza) {
        Ingredient[][] ingredients = pizza.getIngredients();
        for (int i = 0; i < pizza.getRows(); i++) {
            for (int k = 0; k < pizza.getColumns(); k++) {
                if (!ingredients[i][k].isOccupied()) {
                    Slice slice = createSlice(pizza, i, k, cookieCutters);
                    if (slice != null) {
                        pizza.addSlice(slice);
                    }
                }
            }
        }
        return pizza;
    }

    private Slice createSlice(Pizza pizza, int rowNum, int columnNum, List<int[]> cookieCutter) {
        for (int i = 0; i < cookieCutter.size(); i++) {
            if (canCut(pizza, cookieCutter.get(i), rowNum, columnNum)) {
                return new Slice(columnNum, rowNum, columnNum + cookieCutter.get(i)[1] - 1, rowNum + cookieCutter.get(i)[0] - 1);
            }
        }
        return null;
    }

    private boolean canCut(Pizza pizza, int[] cookieCutter, int rowNum, int columnNum) {
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
