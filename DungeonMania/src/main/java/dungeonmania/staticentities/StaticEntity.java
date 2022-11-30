package dungeonmania.staticentities;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class StaticEntity extends Entity {
    public StaticEntity(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }
    
    /**
     * update the entity base on the given object
     * @param o object for update
     * @return void.
     */
    public void update(Object o) {
        
    }
    
    
}

