import com.gameoflife.rules.MakeDeadCellAliveOnNeighbourCellsAreLiveRule;
import com.gameoflife.rules.MakeLiveCellDeadOnNeighboursFewerRule;
import com.gameoflife.rules.MakeLiveCellDeadOnNeighboursOverCrowdedRule;
import com.gameoflife.rules.UniverseRule;
import org.junit.Assert;
import org.junit.Test;

public class UniverseRuleTest {
    @Test
    public void verifyIfRulesDefinedAreWithRespectToAliveCellOrDeadCell() {
        UniverseRule universeRule = new MakeLiveCellDeadOnNeighboursFewerRule();
        boolean isRuleForAliveCell = universeRule.isRuleForAliveCell();
        Assert.assertTrue(isRuleForAliveCell);
        universeRule = new MakeLiveCellDeadOnNeighboursOverCrowdedRule();
        isRuleForAliveCell = universeRule.isRuleForAliveCell();
        Assert.assertTrue(isRuleForAliveCell);
        universeRule = new MakeDeadCellAliveOnNeighbourCellsAreLiveRule();
        isRuleForAliveCell = universeRule.isRuleForAliveCell();
        Assert.assertFalse(isRuleForAliveCell);

    }

    @Test
    public void verifyIfRulesDefinedAreCorrectAsPerTheRequirement() {
        UniverseRule universeRule = new MakeLiveCellDeadOnNeighboursFewerRule();
        boolean isCellAlive = universeRule.isCellAliveByThisRule((long) 1);
        Assert.assertFalse(isCellAlive);
        isCellAlive = universeRule.isCellAliveByThisRule((long) 2);
        Assert.assertTrue(isCellAlive);


        universeRule = new MakeLiveCellDeadOnNeighboursOverCrowdedRule();
        isCellAlive = universeRule.isCellAliveByThisRule((long) 4);
        Assert.assertFalse(isCellAlive);
        isCellAlive = universeRule.isCellAliveByThisRule((long) 2);
        Assert.assertTrue(isCellAlive);

        universeRule = new MakeDeadCellAliveOnNeighbourCellsAreLiveRule();
        isCellAlive = universeRule.isCellAliveByThisRule((long) 3);
        Assert.assertTrue(isCellAlive);
        isCellAlive = universeRule.isCellAliveByThisRule((long) 5);
        Assert.assertFalse(isCellAlive);

    }
}
