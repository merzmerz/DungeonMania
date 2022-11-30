package dungeonmania.Goals;

import dungeonmania.GameManager;
import dungeonmania.collectable.CollectableEntity;

public class TreasureGoal extends Goal implements GoalNode {

    public TreasureGoal(GameManager game) {
        super(game);
    }

    /**
     * Check if all treasure is collected
     * @return void.
     */
    public void checkTreasure() {
        this.setComplete(true);
        GameManager game = this.getGame();
        for (CollectableEntity entity: game.getCollectableEntity()) {
            if (entity.getType().equals("treasure")) {
                this.setComplete(false);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean compute() {
        checkTreasure();
        return this.getComplete();
    }
    
}