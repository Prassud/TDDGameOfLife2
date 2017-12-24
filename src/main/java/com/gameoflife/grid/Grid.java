package com.gameoflife.grid;

import com.gameoflife.cell.Cell;
import com.gameoflife.rules.UniverseRule;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Grid {

    private Set<Cell> aliveCells;

    private Map<Cell, Long> neighbourAliveCelCountMap;

    private List<Cell> nextGenrationDeadCells;

    private List<Cell> nextGenrationAliveCells;
    private Set<Cell> deadCells;

    public Grid(Collection<Cell> listOfCells) {
        this.aliveCells = new HashSet<>();
        this.deadCells = new HashSet<>();
        this.aliveCells.addAll(listOfCells);
        this.neighbourAliveCelCountMap = new HashMap<>(10);
        this.nextGenrationAliveCells = new ArrayList<>(10);
        this.nextGenrationDeadCells = new ArrayList<>(10);
    }

    public Set<Cell> getAliveCellsAfterTheRuleIsApplied(UniverseRule universeRule) {

        aliveCells.forEach(
                eachCell -> {
                    updateEachCellAliveCountInMap(eachCell);
                });

        aliveCells.forEach(eachCell -> {
            applyUniverseRuleOnAliveCells(eachCell, universeRule);
        });

        deadCells.forEach(eachCell -> {
            applyUniverseRuleOnDeadCells(eachCell, universeRule);
        });

        this.aliveCells.removeAll(this.nextGenrationDeadCells);
        this.aliveCells.addAll(this.nextGenrationAliveCells);
        return this.aliveCells;
    }

    private void updateEachCellAliveCountInMap(Cell eachCell) {
        List<Cell> neighbourCells = eachCell.getNeighBourCells();
        long aliveCellCount = 0;
        for (Cell eachNeighbourCell : neighbourCells) {
            if (!this.aliveCells.contains(eachNeighbourCell))
                updateDeadNeighBourCellData(eachNeighbourCell);
            else
                aliveCellCount++;
        }
        neighbourAliveCelCountMap.put(eachCell, aliveCellCount);
    }

    private void updateDeadNeighBourCellData(Cell eachNeighbourCell) {
        this.deadCells.add(eachNeighbourCell);
        Long eachNeighbourCellAliveCount = neighbourAliveCelCountMap.get(eachNeighbourCell);
        eachNeighbourCellAliveCount = eachNeighbourCellAliveCount == null ? 1 : eachNeighbourCellAliveCount+1;
        this.neighbourAliveCelCountMap.put(eachNeighbourCell, eachNeighbourCellAliveCount);
    }

    private void applyUniverseRuleOnDeadCells(Cell eachCell, UniverseRule universeRule) {

        Long neighbourAliveCellCount = neighbourAliveCelCountMap.get(eachCell);
        if (!universeRule.isRuleForAliveCell() && universeRule.isCellAliveByThisRule(neighbourAliveCellCount)) {
            this.nextGenrationAliveCells.add(eachCell);
        }
    }

    private void applyUniverseRuleOnAliveCells(Cell eachCell, UniverseRule universeRule) {
        Long neighbourAliveCellCount = neighbourAliveCelCountMap.get(eachCell);
        if (universeRule.isRuleForAliveCell() && !universeRule.isCellAliveByThisRule(neighbourAliveCellCount)) {
            this.nextGenrationDeadCells.add(eachCell);
        }
    }
}
