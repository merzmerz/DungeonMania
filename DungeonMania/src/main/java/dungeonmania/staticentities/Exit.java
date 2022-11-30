package dungeonmania.staticentities;

import dungeonmania.GameManager;
import dungeonmania.Observer;
import dungeonmania.Player;
import dungeonmania.util.Position;

public class Exit extends StaticEntity implements Observer {
    private boolean exit = false;

    public Exit(String id, Position position) {
        super(id, "exit", position, false);
        this.setCanPass(true);

    }

    
    /**
     * check if the player is at the exit
     * @param game game manager.
     * @return void.
     */
    public void exit_check(GameManager game) {
        Position exit_pos = this.getPosition();
        Player p1 = game.getPlayer();
        if (exit_pos.equals(p1.getPosition())) {
            this.exit = true;
        }
        else {
            this.exit = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            GameManager game = (GameManager) o;  
            exit_check(game);
        }
        
    }

    public boolean getExit() {
        return this.exit;
    }

}
