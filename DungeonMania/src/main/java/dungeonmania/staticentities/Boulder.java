package dungeonmania.staticentities;

import dungeonmania.GameManager;
import dungeonmania.Observer;
import dungeonmania.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Boulder extends StaticEntity implements Observer {

    public Boulder(String id, Position position) {
        super(id, "boulder", position, false);
        this.setCanPass(false);
    }
    

    /**
     * move the boulder in the direction that player push if the boulder 
     * cannot move and player will also not be move.
     * @param game game manager.
     * @return void.
     */
    public void move(GameManager game) {
        Position boulder = this.getPosition();
        Player p1 = game.getPlayer();
        Direction moveDirection = game.getMovement();
        Position player_pos = p1.getPosition().translateBy(moveDirection);
        
        if (player_pos.equals(boulder)) {
            Position new_pos = boulder.translateBy(moveDirection);
            this.setPosition(new_pos);
            Player p = game.getPlayer();
            p.setPosition(player_pos);
            game.setPlayer(p);
            game.setMoved(true); 
        }    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            GameManager game = (GameManager) o;     
            move(game);
        }
    }
}
    

