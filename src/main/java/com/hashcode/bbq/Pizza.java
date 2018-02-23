package com.hashcode.bbq;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private int rows;
    private int columns;
    private int minimumIngredientType;
    private int maximumCellsPerSlice;

    private Ingredient[][] ingredients;
    private List<Slice> slices;

    public Pizza(int rows, int columns, int minimumIngredientType, int maximumCellsPerSlice) {
        this.rows = rows;
        this.columns = columns;
        this.minimumIngredientType = minimumIngredientType;
        this.maximumCellsPerSlice = maximumCellsPerSlice;
        ingredients = new Ingredient[rows][columns];
        slices = new ArrayList<>();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMinimumIngredientType() {
        return minimumIngredientType;
    }

    public int getMaximumCellsPerSlice() {
        return maximumCellsPerSlice;
    }

    public void addIngredient(String ingredient, int i, int j) {
        ingredients[i][j] = new Ingredient(ingredient);
    }

    public void setIngredients(Ingredient[][] ingredients) {
        this.ingredients = ingredients;
    }

    public Ingredient[][] getIngredients() {
        return ingredients;
    }

    public void addSlice(Slice slice) {
        for (int i = slice.getRowInit(); i <= slice.getRowEnd(); i++) {
            for (int j = slice.getColumnInit(); j <= slice.getColumnEnd(); j++) {
                ingredients[i][j].setOccupied(true);
            }
        }
        slices.add(slice);
    }

    public List<Slice> getSlices() {
        return slices;
    }

    public int getSize() {
        return rows * columns;
    }

    public Pizza clone() {
        Pizza cloned = new Pizza(rows, columns, minimumIngredientType, maximumCellsPerSlice);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cloned.addIngredient(ingredients[i][j].getIngredient().name(), i, j);
            }
        }
        return cloned;

    }
}
