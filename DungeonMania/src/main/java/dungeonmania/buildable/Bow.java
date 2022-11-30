package dungeonmania.buildable;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Durability;
import dungeonmania.Entity;
import dungeonmania.GameManager;

public class Bow extends BuildableEntity implements Durability{

    GameManager tmpManager;

    public Bow(String id) {
        super(id, "bow");
        super.setDurability(5);
    }

    @Override
    public void updateDurability(GameManager gameManager) {
        tmpManager = gameManager;
        if (this.getDurability() <= 0) {
            List<Entity> inventory = new ArrayList<>();
            for (Entity e : tmpManager.getInventoryList()) {
                if (!e.getType().equals(this.getType())) {
                    inventory.add(e);
                }
            }
            tmpManager.setInventoryList(inventory);
        }

    }
    
}
