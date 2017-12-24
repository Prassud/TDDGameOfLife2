import com.gameoflife.Universe;
import com.gameoflife.cell.Cell;
import com.gameoflife.grid.Grid;
import com.gameoflife.rules.MakeDeadCellAliveOnNeighbourCellsAreLiveRule;
import com.gameoflife.rules.MakeLiveCellDeadOnNeighboursFewerRule;
import com.gameoflife.rules.MakeLiveCellDeadOnNeighboursOverCrowdedRule;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        MakeLiveCellDeadOnNeighboursFewerRule firstRule = mock(MakeLiveCellDeadOnNeighboursFewerRule.class);
        when(firstRule.isCellAliveByThisRule(anyLong())).thenReturn(true);
        Set<Cell> currentGenerationAliveCells = gameOfLifeGrid.getAliveCellsAfterTheRuleIsApplied(firstRule);
        currentGenerationAliveCells.forEach(eachCell -> Assert.assertTrue(listOfCell.contains(eachCell)));

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
        MakeLiveCellDeadOnNeighboursFewerRule firstRule = mock(MakeLiveCellDeadOnNeighboursFewerRule.class);
        Grid grid = new Grid(listOfCells);
        Universe universe = new Universe(grid, firstRule);
        String nextGenerationResult = universe.tick();
        verify(firstRule, times(3)).isCellAliveByThisRule(anyLong());


        listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        grid = new Grid(listOfCells);
        firstRule = new MakeLiveCellDeadOnNeighboursFewerRule();
        universe = new Universe(grid, firstRule);
        nextGenerationResult = universe.tick();
        Assert.assertEquals(expectedGenerationResult, nextGenerationResult);
    }

    @Test
    public void verifyIfFirstAndSecondRuleIsAppliedOnGameOfLife() {
        Cell cell = new Cell(1, 0);
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        Cell cell3 = new Cell(0, 1);
        Cell cell4 = new Cell(2, 1);
        String expectedGenerationResult[] = new String[]{"1,0", "1,2", "0,1", "2,1"};
        List<Cell> listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        listOfCells.add(cell3);
        listOfCells.add(cell4);
        MakeLiveCellDeadOnNeighboursOverCrowdedRule secondRule = mock(MakeLiveCellDeadOnNeighboursOverCrowdedRule.class);
        Grid grid = new Grid(listOfCells);
        Universe universe = new Universe(grid, secondRule);
        String nextGenerationResult = universe.tick();
        verify(secondRule, times(5)).isCellAliveByThisRule(anyLong());


        listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        listOfCells.add(cell3);
        listOfCells.add(cell4);
        grid = new Grid(listOfCells);
        secondRule = new MakeLiveCellDeadOnNeighboursOverCrowdedRule();
        universe = new Universe(grid, secondRule);
        nextGenerationResult = universe.tick();
        String finalNextGenerationResult = nextGenerationResult;
        Arrays.stream(expectedGenerationResult).forEach(eachResult -> {
            System.out.printf(finalNextGenerationResult);
            Assert.assertTrue(finalNextGenerationResult.contains(eachResult)
            );
        });

    }


    @Test
    public void verifyIfThirdRuleIsAppliedOnGameOfLife() {
        Cell cell = new Cell(1, 0);
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        Cell cell3 = new Cell(0, 1);
        Cell cell4 = new Cell(2, 1);
        String expectedGenerationResult[] = new String[]{
                "1,0", "1,2", "0,1", "2,1", "0,0", "0,2", "2,2", "2,1"
        };
        List<Cell> listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        listOfCells.add(cell3);
        listOfCells.add(cell4);
        MakeDeadCellAliveOnNeighbourCellsAreLiveRule thirdRule = mock(MakeDeadCellAliveOnNeighbourCellsAreLiveRule.class);
        when(thirdRule.isCellAliveByThisRule(anyLong())).thenReturn(false);
        Grid grid = new Grid(listOfCells);
        Universe universe = new Universe(grid, thirdRule);
        String nextGenerationResult = universe.tick();
        verify(thirdRule, times(16)).isCellAliveByThisRule(anyLong());
        listOfCells = new ArrayList<>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        listOfCells.add(cell3);
        listOfCells.add(cell4);
        grid = new Grid(listOfCells);
        thirdRule = new MakeDeadCellAliveOnNeighbourCellsAreLiveRule();
        universe = new Universe(grid, thirdRule);
        nextGenerationResult = universe.tick();
        String finalNextGenerationResult = nextGenerationResult;
        Arrays.stream(expectedGenerationResult).forEach(eachResult -> {
            System.out.printf(finalNextGenerationResult);
            Assert.assertTrue(finalNextGenerationResult.contains(eachResult)
            );
        });

    }
    

}
