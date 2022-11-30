package dungeonmania.MovingEntity;

import dungeonmania.Entity;
import dungeonmania.util.Position;


public abstract class MovingEntity extends Entity {
    private int health;
    private int atkDamage;
    
    /**
     * Set moving entity.
     * @param id moving entity's id
     * @param type moving entity's type
     * @param position moving entity's position
     * @param isInteractable moving entity's interactable
     */
    public MovingEntity(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    /**
     * Move entity and return new position.
     * @param entityPos Take client position.
     * @return Return new entity's position after set new position to entity.
     */
    public abstract Position move(Position entityPos);

    /**
     * Set moving strategy
     * @param strat strategy
     */
    public abstract void setMovingStrategy(String strat);

    public void setHealth(int health) { this.health = health; }
    public void setAtkDamage(int atkDamage) { this.atkDamage = atkDamage; }
    
    public int getHealth() { return health; }
    public int getAtkDamage() { return atkDamage; }
}
