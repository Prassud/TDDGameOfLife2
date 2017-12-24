package com.gameoflife.rules;

public interface UniverseRule {

    boolean isCellAliveByThisRule(Long neighbourAliveCellCount);

    boolean isRuleForAliveCell();
}
