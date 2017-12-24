package com.gameoflife;

import com.gameoflife.cell.Cell;
import com.gameoflife.grid.Grid;
import com.gameoflife.rules.UniverseRule;

import java.util.List;

public class Universe {
    private Grid grid;

    private UniverseRule universeRule;

    public Universe(Grid grid, UniverseRule universeRule) {
        this.grid = grid;
        this.universeRule = universeRule;
    }

    public String tick() {

        List<Cell> aliveCellsAfterTheRuleIsApplied = this.grid.getAliveCellsAfterTheRuleIsApplied(universeRule);
        return aliveCellsAfterTheRuleIsApplied.stream().map(eachCell -> eachCell.getxCoordinate() + "," + eachCell.getyCoordinate()).reduce("", String::concat);
    }
}
