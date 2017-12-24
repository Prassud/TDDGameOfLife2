import com.gameoflife.Universe;
import com.gameoflife.grid.Grid;
import com.gameoflife.cell.Cell;
import com.gameoflife.rules.MakeLiveCelDeadRule;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class GameOfLifeTest {
    @Test
    public void verifyIfGridCanBeCreatedWithGiveCellCoordinates() {
        Cell cell = new Cell(1, 2);
        Cell cell1 = new Cell(1, 2);
        Cell cell2 = new Cell(1, 2);
        Cell cell3 = new Cell(1, 2);
        Cell cell4 = new Cell(1, 2);
        List<Cell> listOfCell = new ArrayList<Cell>(10);
        listOfCell.add(cell);
        listOfCell.add(cell1);
        listOfCell.add(cell2);
        listOfCell.add(cell3);
        listOfCell.add(cell4);
        Grid gameOfLifeGrid = new Grid(listOfCell);
        MakeLiveCelDeadRule firstRule = mock(MakeLiveCelDeadRule.class);
        when(firstRule.isCellAliveByThisRule(anyLong())).thenReturn(true);
        List<Cell> currentGenerationAliveCells = gameOfLifeGrid.getAliveCellsAfterTheRuleIsApplied(firstRule);
        currentGenerationAliveCells.forEach(eachCell -> Assert.assertTrue(listOfCell.contains(eachCell)));

    }

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

        List<Cell> neighbourCells = currCell.getNeighBourCells();
        neighbourCells.forEach(eachNeighbourCell -> assertTrue(expectedNeighBourCells.contains(eachNeighbourCell)));

    }

    @Test
    public void verifyIfFirstRuleIsAppliedOnGameOfLife() {
        Cell cell = new Cell(1, 0);
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        String expectedGenerationResult = "1,1";
        List<Cell> listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        MakeLiveCelDeadRule firstRule = mock(MakeLiveCelDeadRule.class);
        Grid grid = new Grid(listOfCells);
        Universe universe = new Universe(grid, firstRule);
        String nextGenerationResult = universe.tick();
        verify(firstRule, times(3)).isCellAliveByThisRule(anyLong());


        listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        grid = new Grid(listOfCells);
        firstRule = new MakeLiveCelDeadRule();
        universe = new Universe(grid, firstRule);
        nextGenerationResult = universe.tick();
        Assert.assertEquals(expectedGenerationResult, nextGenerationResult);
    }

}
