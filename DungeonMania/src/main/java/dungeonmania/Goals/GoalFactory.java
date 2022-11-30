package dungeonmania.Goals;

import dungeonmania.GameManager;

public class GoalFactory {
    
    public static GoalNode createGoal(String goal, GameManager game) {
        if (goal.equals("exit")) {
            return new ExitGoal(game);
        } else if (goal.equals("boulder")) {
            return new SwitchGoal(game);
        } else if (goal.equals("treasure")) {
            return new TreasureGoal(game);
        } else if (goal.equals("enemies")) {
            return new EnemyGoal(game);
        }
        
        return null;
    }
}
