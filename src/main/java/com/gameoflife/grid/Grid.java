package com.gameoflife.grid;

import com.gameoflife.cell.Cell;
import com.gameoflife.rules.UniverseRule;

import java.util.*;

public class Grid {

    private Set<Cell> aliveCells;

    private Map<Cell, Long> neighbourAliveCellCountMap;

    private List<Cell> nextGenerationDeadCells;

    private List<Cell> nextGenerationAliveCells;
    private Set<Cell> deadCells;

    public Grid(Collection<Cell> listOfCells) {
        this.aliveCells = new HashSet<>();
        this.deadCells = new HashSet<>();
        this.aliveCells.addAll(listOfCells);
        this.neighbourAliveCellCountMap = new HashMap<>(10);
        this.nextGenerationAliveCells = new ArrayList<>(10);
        this.nextGenerationDeadCells = new ArrayList<>(10);
    }

    public Set<Cell> getAliveCellsAfterTheRuleIsApplied(List<UniverseRule> universeRules) {

        this.aliveCells.forEach(this::updateEachCellAliveCountInMap);

        this.aliveCells.forEach(eachCell -> {
            applyUniverseRuleOnAliveCells(eachCell, universeRules);
        });

        this.deadCells.forEach(eachCell -> {
            applyUniverseRuleOnDeadCells(eachCell, universeRules);
        });

        this.aliveCells.removeAll(this.nextGenerationDeadCells);
        this.aliveCells.addAll(this.nextGenerationAliveCells);
        clearAll();
        return this.aliveCells;
    }

    private void clearAll() {
        this.neighbourAliveCellCountMap.clear();
        this.deadCells.clear();
        this.nextGenerationAliveCells.clear();
        this.nextGenerationDeadCells.clear();
    }

    private void updateEachCellAliveCountInMap(Cell eachCell) {
        List<Cell> neighbourCells = eachCell.getNeighbourCells();
        long aliveCellCount = 0;
        for (Cell eachNeighbourCell : neighbourCells) {
            if (!this.aliveCells.contains(eachNeighbourCell))
                updateDeadNeighBourCellData(eachNeighbourCell);
            else
                aliveCellCount++;
        }
        neighbourAliveCellCountMap.put(eachCell, aliveCellCount);
    }

    private void updateDeadNeighBourCellData(Cell eachNeighbourCell) {
        this.deadCells.add(eachNeighbourCell);
        Long eachNeighbourCellAliveCount = neighbourAliveCellCountMap.get(eachNeighbourCell);
        eachNeighbourCellAliveCount = eachNeighbourCellAliveCount == null ? 1 : eachNeighbourCellAliveCount + 1;
        this.neighbourAliveCellCountMap.put(eachNeighbourCell, eachNeighbourCellAliveCount);
    }

    private void applyUniverseRuleOnDeadCells(Cell eachCell, List<UniverseRule> universeRules) {

        Long neighbourAliveCellCount = neighbourAliveCellCountMap.get(eachCell);
        universeRules.stream().filter(eachUniverseRule -> {
            if (!eachUniverseRule.isRuleForAliveCell() && eachUniverseRule.isCellAliveByThisRule(neighbourAliveCellCount)) {
                this.nextGenerationAliveCells.add(eachCell);
                return true;
            }
            return false;
        }).findFirst();


    }

    private void applyUniverseRuleOnAliveCells(Cell eachCell, List<UniverseRule> universeRules) {
        Long neighbourAliveCellCount = neighbourAliveCellCountMap.get(eachCell);
        universeRules.stream().filter(eachUniverseRule -> {
            if (eachUniverseRule.isRuleForAliveCell() && !eachUniverseRule.isCellAliveByThisRule(neighbourAliveCellCount)) {
                this.nextGenerationDeadCells.add(eachCell);
                return true;
            }
            return false;
        }).findFirst();

    }
}
