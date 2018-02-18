package com.hashcode.bbq;

public class Ingredient {
    public enum IngredientEnum {
        M,
        T
    }

    private IngredientEnum ingredient;
    private boolean occupied;

    public Ingredient(String ingredient) {
        this.ingredient = IngredientEnum.valueOf(ingredient);
        occupied = false;
    }

    public IngredientEnum getIngredient() {
        return ingredient;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
