package com.gameoflife.rules;

public class MakeLiveCellDeadOnNeighboursOverCrowdedRule implements UniverseRule {
    @Override
    public boolean isCellAliveByThisRule(Long neighbourAliveCellCount) {
        if (neighbourAliveCellCount == null || neighbourAliveCellCount > 3) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isRuleForAliveCell() {
        return true;
    }
}
