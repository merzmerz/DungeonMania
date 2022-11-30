package dungeonmania.Goals;

import dungeonmania.GameManager;

public class Goal {
    private boolean complete;
    private GameManager game;

    public Goal(GameManager game) {
        this.game = game;
        this.complete = false;
    }

    public boolean getComplete() {
        return complete;
    }

    public GameManager getGame() {
        return this.game;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

   


    
}
