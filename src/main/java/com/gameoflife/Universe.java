package com.gameoflife;

import com.gameoflife.cell.Cell;
import com.gameoflife.grid.Grid;
import com.gameoflife.rules.UniverseRule;

import java.util.List;
import java.util.Set;

public class Universe {
    private Grid grid;

    private List<UniverseRule> universeRules;

    public Universe(Grid grid, List<UniverseRule> listOfUniverseRules) {
        this.grid = grid;
        this.universeRules = listOfUniverseRules;
    }

    public String tick() {

        Set<Cell> aliveCellsAfterTheRuleIsApplied = this.grid.getAliveCellsAfterTheRuleIsApplied(universeRules);
        return aliveCellsAfterTheRuleIsApplied.stream().map(
                eachCell ->
                {
                    String appendedResult = eachCell.getxCoordinate() + "," + eachCell.getyCoordinate() + '|';
                    return appendedResult;
                }).
                reduce("", String::concat);
    }
}
