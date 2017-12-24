package com.gameoflife.rules;

import com.gameoflife.cell.Cell;

public class MakeLiveCelDeadRule implements UniverseRule {

    @Override
    public boolean isCellAliveByThisRule(Long neighbourAliveCellCount) {
        if (neighbourAliveCellCount == null || neighbourAliveCellCount < 2)
            return false;
        return true;
    }
}
