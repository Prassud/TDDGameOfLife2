package com.gameoflife;

import com.gameoflife.cell.Cell;
import com.gameoflife.grid.Grid;
import com.gameoflife.rules.UniverseRule;

import java.util.Set;

public class Universe {
    private Grid grid;

    private UniverseRule universeRule;

    public Universe(Grid grid, UniverseRule universeRule) {
        this.grid = grid;
        this.universeRule = universeRule;
    }

    public String tick() {

        Set<Cell> aliveCellsAfterTheRuleIsApplied = this.grid.getAliveCellsAfterTheRuleIsApplied(universeRule);
        return aliveCellsAfterTheRuleIsApplied.stream().map(
                eachCell ->
                {
                    String appendedResult = eachCell.getxCoordinate() + "," + eachCell.getyCoordinate() + System.lineSeparator();
                    return  appendedResult;
                }).
                reduce("", String::concat);
    }
}
