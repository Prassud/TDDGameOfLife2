package com.gameoflife.universe;

import com.gameoflife.Universe;
import com.gameoflife.cell.Cell;
import com.gameoflife.grid.Grid;
import com.gameoflife.rules.MakeDeadCellAliveOnNeighbourCellsAreLiveRule;
import com.gameoflife.rules.MakeLiveCellDeadOnNeighboursFewerRule;
import com.gameoflife.rules.MakeLiveCellDeadOnNeighboursOverCrowdedRule;
import com.gameoflife.rules.UniverseRule;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class    GameOfLifeTest {
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

        List<UniverseRule> rules = new ArrayList<>();

        Set<Cell> currentGenerationAliveCells = gameOfLifeGrid.getAliveCellsAfterTheRuleIsApplied(rules);
        currentGenerationAliveCells.forEach(eachCell -> Assert.assertTrue(listOfCell.contains(eachCell)));

    }


    @Test
    public void verifyIfFirstRuleIsAppliedOnGameOfLife() {
        Cell cell = new Cell(1, 0);
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        String expectedGenerationResult = "1,1" + '|';
        List<Cell> listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        MakeLiveCellDeadOnNeighboursFewerRule firstRule = Mockito.mock(MakeLiveCellDeadOnNeighboursFewerRule.class);
        Mockito.when(firstRule.isRuleForAliveCell()).thenReturn(true);
        Grid grid = new Grid(listOfCells);
        ArrayList<UniverseRule> rules = new ArrayList<>();
        rules.add(firstRule);
        Universe universe = new Universe(grid, rules);
        String nextGenerationResult = universe.tick();
        Mockito.verify(firstRule, Mockito.times(3)).isCellAliveByThisRule(Matchers.anyLong());


        listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        grid = new Grid(listOfCells);
        firstRule = new MakeLiveCellDeadOnNeighboursFewerRule();
        rules = new ArrayList<>();
        rules.add(firstRule);
        universe = new Universe(grid, rules);
        nextGenerationResult = universe.tick();
        Assert.assertEquals(expectedGenerationResult, nextGenerationResult);
    }

    @Test
    public void verifyIfSecondRuleIsAppliedOnGameOfLife() {
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
        MakeLiveCellDeadOnNeighboursOverCrowdedRule secondRule = Mockito.mock(MakeLiveCellDeadOnNeighboursOverCrowdedRule.class);
        Mockito.when(secondRule.isRuleForAliveCell()).thenReturn(true);
        Grid grid = new Grid(listOfCells);

        List<UniverseRule> rules = new ArrayList<>();
        rules.add(secondRule);

        Universe universe = new Universe(grid, rules);
        String nextGenerationResult = universe.tick();
        Mockito.verify(secondRule, Mockito.times(5)).isCellAliveByThisRule(Matchers.anyLong());


        listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        listOfCells.add(cell3);
        listOfCells.add(cell4);
        grid = new Grid(listOfCells);
        secondRule = new MakeLiveCellDeadOnNeighboursOverCrowdedRule();
        rules = new ArrayList<>();
        rules.add(secondRule);
        universe = new Universe(grid, rules);
        nextGenerationResult = universe.tick();
        String finalNextGenerationResult = nextGenerationResult;
        Arrays.stream(expectedGenerationResult).forEach(eachResult -> {

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
        MakeDeadCellAliveOnNeighbourCellsAreLiveRule thirdRule = Mockito.mock(MakeDeadCellAliveOnNeighbourCellsAreLiveRule.class);
        Mockito.when(thirdRule.isCellAliveByThisRule(Matchers.anyLong())).thenReturn(false);
        Mockito.when(thirdRule.isRuleForAliveCell()).thenReturn(false);
        List<UniverseRule> rules = new ArrayList<>();
        rules.add(thirdRule);
        Grid grid = new Grid(listOfCells);
        Universe universe = new Universe(grid, rules);
        String nextGenerationResult = universe.tick();
        Mockito.verify(thirdRule, Mockito.times(16)).isCellAliveByThisRule(Matchers.anyLong());
        listOfCells = new ArrayList<>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        listOfCells.add(cell3);
        listOfCells.add(cell4);
        grid = new Grid(listOfCells);
        thirdRule = new MakeDeadCellAliveOnNeighbourCellsAreLiveRule();
        rules = new ArrayList<>();
        rules.add(thirdRule);
        universe = new Universe(grid, rules);
        nextGenerationResult = universe.tick();
        String finalNextGenerationResult = nextGenerationResult;
        Arrays.stream(expectedGenerationResult).forEach(eachResult -> {

            Assert.assertTrue(finalNextGenerationResult.contains(eachResult)
            );
        });

    }


    @Test
    public void verifyIfAllRulesAreAppliedOnGameOfLife() {
        Cell cell = new Cell(1, 0);
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        String expectedGenerationResult[] = new String[]{"1,1", "0,1", "2,1"};
        List<Cell> listOfCells = new ArrayList<Cell>(10);
        listOfCells.add(cell);
        listOfCells.add(cell1);
        listOfCells.add(cell2);
        List<UniverseRule> listOfRules = new ArrayList<>(10);
        listOfRules.add(new MakeDeadCellAliveOnNeighbourCellsAreLiveRule());
        listOfRules.add(new MakeLiveCellDeadOnNeighboursOverCrowdedRule());
        listOfRules.add(new MakeLiveCellDeadOnNeighboursFewerRule());


        Grid grid = new Grid(listOfCells);
        Universe universe = new Universe(grid, listOfRules);
        String nextGenerationResult = universe.tick();

        String[] finalNextGenerationResult = nextGenerationResult.split("\\|");
        Arrays.sort(finalNextGenerationResult);
        Arrays.sort(expectedGenerationResult);
        Assert.assertTrue(Arrays.equals(expectedGenerationResult, finalNextGenerationResult));

    }


}
