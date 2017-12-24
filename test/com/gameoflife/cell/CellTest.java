package com.gameoflife.cell;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CellTest {

    @Test
    public void verifyIfNeighbourCellsOfCurrentCellsAreProvided() {

        int xCoordinate = 0;
        int yCoordinate = 0;
        Cell currCell = new Cell(xCoordinate, yCoordinate);
        List<Cell> expectedNeighBourCells = new ArrayList<Cell>(10);
        expectedNeighBourCells.add(new Cell(xCoordinate - 1, yCoordinate - 1));
        expectedNeighBourCells.add(new Cell(xCoordinate - 1, yCoordinate + 1));
        expectedNeighBourCells.add(new Cell(xCoordinate, yCoordinate + 1));
        expectedNeighBourCells.add(new Cell(xCoordinate, yCoordinate - 1));
        expectedNeighBourCells.add(new Cell(xCoordinate + 1, yCoordinate + 1));
        expectedNeighBourCells.add(new Cell(xCoordinate + 1, yCoordinate - 1));
        expectedNeighBourCells.add(new Cell(xCoordinate + 1, yCoordinate));
        expectedNeighBourCells.add(new Cell(xCoordinate - 1, yCoordinate));

        List<Cell> neighbourCells = currCell.getNeighbourCells();
        neighbourCells.forEach(eachNeighbourCell -> assertTrue(expectedNeighBourCells.contains(eachNeighbourCell)));

    }
}
