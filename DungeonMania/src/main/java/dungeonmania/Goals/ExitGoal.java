package dungeonmania.Goals;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.staticentities.Exit;

public class ExitGoal extends Goal implements GoalNode {

    public ExitGoal(GameManager game) {
        super(game);
    }

    public void checkExit() {
        GameManager game = this.getGame();
        for (Entity entity: game.getEntityList()) {
            if (entity.getType().equals("exit")) {
                Exit exit = (Exit) entity;
                exit.exit_check(game);
                if (exit.getExit()) {
                    this.setComplete(true);
                }
            }
        }
    }

    @Override
    public boolean compute() {
        checkExit();
        return this.getComplete();
    }
    
}
