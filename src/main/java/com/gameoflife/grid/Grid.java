package com.gameoflife.grid;

import com.gameoflife.Universe;
import com.gameoflife.cell.Cell;
import com.gameoflife.rules.UniverseRule;

import java.util.List;
import java.util.Map;

public class Grid {

    private List<Cell> aliveCells;

    private Map<Cell, Long> neighbourAliveCelCountMap;

    public Grid(List<Cell> listOfCells) {
        this.aliveCells = listOfCells;
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
        return this.aliveCells;
    }

    private void applyFirstRule(Cell eachCell, UniverseRule universeRule) {
        Long neighbourAliveCellCount = neighbourAliveCelCountMap.get(eachCell);
        if (!universeRule.isCellAliveByThisRule(neighbourAliveCellCount)) {
            aliveCells.remove(eachCell);
        }
    }
}
