package dungeonmania.Goals;

import dungeonmania.GameManager;
import dungeonmania.MovingEntity.MovingEntity;
import dungeonmania.MovingEntity.Mercenary.Mercenary;

public class EnemyGoal extends Goal implements GoalNode {

    public EnemyGoal(GameManager game) {
        super(game);
    }


    /**
     * Check if all enemies is eliminated
     * @return void.
     */
    public void checkEnemy() {
        this.setComplete(true);
        GameManager game = this.getGame();
        for (MovingEntity entity: game.getMovingEntites()) {
            if (entity.getType().equals("zombie_toast")) {
                this.setComplete(false);
            }
            if (entity.getType().equals("spider")) {
                this.setComplete(false);
            }
            if (entity.getType().equals("mercenary")) {
                Mercenary mercen = (Mercenary) entity;
                if (!mercen.getIsBribed()) {
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
        checkEnemy();
        return this.getComplete();
    }
    
}
