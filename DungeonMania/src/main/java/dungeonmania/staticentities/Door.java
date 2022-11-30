package dungeonmania.staticentities;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Observer;
import dungeonmania.Player;
import dungeonmania.util.Position;

public class Door extends StaticEntity implements Observer {

    public Door(String id, Position position) {
        super(id, "door", position, false);
        this.setCanPass(false);
    
    }

    /**
     * Open the door if the player meet the requirement 
     * @param game game manager.
     * @return void.
     */
    public void open_door(GameManager game) {
        Player p1 = game.getPlayer();
        Position player_pos = p1.getPosition();
        Position door_pos = this.getPosition();
        if (check_key(game)) {
            this.setCanPass(true);
        }
        if (player_pos.equals(door_pos) && check_key(game)) {
            this.setType("door_unlocked");
            this.setCanPass(true);    
        }
    }

    /**
     * check if player have key
     * @param game game manager.
     * @return boolean.
     */
    public boolean check_key(GameManager game) {
        for (Entity entity : game.getInventoryList()) {
            if (entity.getType().equals("key")){
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            GameManager game = (GameManager) o;     
            open_door(game);
        }
    } 
}
