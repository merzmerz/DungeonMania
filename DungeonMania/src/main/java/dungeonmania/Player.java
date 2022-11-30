package dungeonmania;

import java.util.ArrayList;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.collectable.CollectableEntity;

public class Player extends Entity implements Subject {
    private ArrayList<Observer> Entities = new ArrayList<Observer>();
    private final ArrayList<CollectableEntity> inventory = new ArrayList<CollectableEntity>();
    private int health;
    private int attackDamage;
    private boolean isInvincible;
    private boolean isInvisible;
    private Direction movement;

    public Player(int health, int attack, String id, Position position) {
        super("player", "player", position, false);
        this.health = health;
        this.attackDamage = attack;
        this.isInvincible = false;
        this.isInvisible = false;
    }

    @Override
    public void Attach(Observer entity) {
        Entities.add(entity);
    }

    @Override
    public void Detach(Observer entity) {
        Entities.remove(entity);
    }

    @Override
    public void Notify() {
        for (Observer entity : Entities) {
            entity.update(this);
        }
    }

    public boolean getIsInvincible() {
        return this.isInvincible;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void addItem(CollectableEntity item) {
        inventory.add(item);
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    public boolean isInvisible() {
        return isInvisible;
    }

    public void setInvisible(boolean isInvisible) {
        this.isInvisible = isInvisible;
    }

    public Direction getMovement() {
        return this.movement;
    }

    public void move(Direction movementDirection) {
        this.addToEntityHistory(new Player(health, attackDamage, "player", this.getPosition()));
        setPosition(getPosition().translateBy(movementDirection));
        this.movement = movementDirection;
    }
}
