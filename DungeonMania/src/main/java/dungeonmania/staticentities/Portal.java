package dungeonmania.staticentities;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Observer;
import dungeonmania.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position; 

public class Portal extends StaticEntity implements Observer {

    public Portal(String id, Position position) {
        super(id, "portal", position, false);
        this.setCanPass(true);
    }

    /**
     * move the boulder in the direction that player push if the boulder 
     * cannot move and player will also not be move.
     * @param game game manager.
     * @param Pair portals.
     * @return void.
     */
    public void teleport(GameManager game, Pair<Entity, Entity> portals) {
        Player p1 = game.getPlayer();
        Direction movement = game.getMovement();
        Position player_pos = p1.getPosition();
        Entity portal_1 = portals.getKey();
        Entity portal_2 = portals.getValue();
        
        if (player_pos.equals(portal_1.getPosition())) {
            p1.setPosition(portal_2.getPosition().translateBy(movement));
        }

        if (player_pos.equals(portal_2.getPosition())) {
            p1.setPosition(portal_1.getPosition().translateBy(movement));
        }
    }

    /**
     * move the boulder in the direction that player push if the boulder 
     * cannot move and player will also not be move.
     * @param game game manager.
     * @return void.
     */
    public void setUpPortal(GameManager game) {
        Entity portal_1 = this;
        Entity portal_2 = null;
        for (Entity e: game.getEntityList()) {
            if (e.getType().equals("portal") && !e.equals(this)) {
                portal_2 = e;
            }
        }
        Pair<Entity, Entity> portals = new OrderedPair<Entity,Entity>(portal_1, portal_2);
        teleport(game, portals);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            GameManager game = (GameManager) o;     
            setUpPortal(game);
        }
    }

}
