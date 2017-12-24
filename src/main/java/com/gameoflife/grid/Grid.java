package com.gameoflife.grid;

import com.gameoflife.Universe;
import com.gameoflife.cell.Cell;
import com.gameoflife.rules.UniverseRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

    private List<Cell> aliveCells;

    private Map<Cell, Long> neighbourAliveCelCountMap;

    private List<Cell> nextGenrationDeadCells;

    private List<Cell> nextGenrationAliveCells;

    public Grid(List<Cell> listOfCells) {
        this.aliveCells = listOfCells;
        this.neighbourAliveCelCountMap = new HashMap<>(10);
        this.nextGenrationAliveCells = new ArrayList<>(10);
        this.nextGenrationDeadCells = new ArrayList<>(10);
    }

    public List<Cell> getAliveCellsAfterTheRuleIsApplied(UniverseRule universeRule) {

        aliveCells.forEach(
                eachCell -> {
                    List<Cell> neighbourCells = eachCell.getNeighBourCells();
                    long aliveCellCount = neighbourCells.stream().filter(this.aliveCells::contains).count();
                    neighbourAliveCelCountMap.put(eachCell, aliveCellCount);
                });
        aliveCells.forEach(eachCell -> {
            applyFirstRule(eachCell, universeRule);
        });
        this.aliveCells.removeAll(this.nextGenrationDeadCells);
        return this.aliveCells;
    }

    private void applyFirstRule(Cell eachCell, UniverseRule universeRule) {
        Long neighbourAliveCellCount = neighbourAliveCelCountMap.get(eachCell);
        if (!universeRule.isCellAliveByThisRule(neighbourAliveCellCount)) {
            this.nextGenrationDeadCells.add(eachCell);
        }
    }
}
