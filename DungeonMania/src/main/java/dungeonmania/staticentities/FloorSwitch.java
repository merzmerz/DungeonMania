package dungeonmania.staticentities;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Observer;
import dungeonmania.util.Position;

public class FloorSwitch extends StaticEntity implements Observer {
    private boolean trigger = false;

    public FloorSwitch(String id, Position position) {
        super(id, "switch", position, false);
        this.setCanPass(true);
    }


    /**
     * set the trigger on the floor switch if condition is met
     * @param game game manager.
     * @return void.
     */
    public void setTrigger(GameManager game) {
        for (Entity entity: game.getStaticEntities()) {
            // check every boulder in the map
            if (entity.getType().equals("boulder")) {
                // if align with this floor switch set trigger true.
                Position boulder_pos = entity.getPosition();
                if (this.getPosition().equals(boulder_pos)) {
                    this.trigger = true;
                    break;
                }
                else {
                    this.trigger = false;
                }          
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            GameManager game = (GameManager) o;  
            setTrigger(game);
        }
    }

    public boolean getTrigger() {
        return this.trigger;
    }

    
}