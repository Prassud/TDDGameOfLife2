package com.gameoflife.rules;

public class MakeDeadCellAliveOnNeighbourCellsAreLiveRule implements UniverseRule {


    @Override
    public boolean isCellAliveByThisRule(Long neighbourAliveCellCount) {
        if (neighbourAliveCellCount != null && (neighbourAliveCellCount == 3)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRuleForAliveCell() {
        return false;
    }
}
