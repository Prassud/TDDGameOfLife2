package com.gameoflife.cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {
    private int xCoordinate;
    private int yCoordinate;

    public Cell(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return xCoordinate == cell.xCoordinate &&
                yCoordinate == cell.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    public List<Cell> getNeighBourCells() {
        List<Cell> neighBourCellsToCurrentCells = new ArrayList<>(10);
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate - 1, yCoordinate - 1));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate - 1, yCoordinate + 1));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate, yCoordinate + 1));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate, yCoordinate - 1));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate + 1, yCoordinate + 1));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate + 1, yCoordinate - 1));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate + 1, yCoordinate));
        neighBourCellsToCurrentCells.add(new Cell(xCoordinate - 1, yCoordinate));
        return neighBourCellsToCurrentCells;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
}
