package com.hashcode.bbq;

public class Slice {
    private int columnInit;
    private int rowInit;
    private int columnEnd;
    private int rowEnd;

    public Slice(int columnInit, int rowInit, int columnEnd, int rowEnd) {
        this.columnInit = columnInit;
        this.rowInit = rowInit;
        this.columnEnd = columnEnd;
        this.rowEnd = rowEnd;
    }

    public int getColumnInit() {
        return columnInit;
    }

    public int getRowInit() {
        return rowInit;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getNumCells() {
        return (rowEnd - rowInit + 1) * (columnEnd - columnInit + 1);
    }
}
