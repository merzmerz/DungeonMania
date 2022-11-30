package dungeonmania.Goals;

import dungeonmania.GameManager;
import dungeonmania.staticentities.FloorSwitch;
import dungeonmania.staticentities.StaticEntity;

public class SwitchGoal extends Goal implements GoalNode {

    public SwitchGoal(GameManager game) {
        super(game);
        this.setComplete(true);
    }

    /**
     * Check if all switch is triggered
     * @return void.
     */
    public void checkSwitch() {
        this.setComplete(true);
        GameManager game = this.getGame();
        for (StaticEntity entity: game.getStaticEntities()) {
            if (entity.getType().equals("switch")) {
                FloorSwitch sw = (FloorSwitch) entity;
                sw.setTrigger(game);
                if (sw.getTrigger() == false) {
                    this.setComplete(false);
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean compute() {
        checkSwitch();
        return this.getComplete();
    }

    
}
