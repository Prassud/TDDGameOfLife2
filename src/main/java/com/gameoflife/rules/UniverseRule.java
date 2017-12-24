package com.gameoflife.rules;

import com.gameoflife.cell.Cell;

public interface UniverseRule {

    boolean isCellAliveByThisRule(Long neighbourAliveCellCount);
}
