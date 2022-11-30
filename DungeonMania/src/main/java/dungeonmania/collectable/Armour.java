package dungeonmania.collectable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dungeonmania.Durability;
import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Player;
import dungeonmania.util.Position;

public class Armour extends CollectableEntity implements Durability{

    Player player;
    GameManager tmpManager;
    List<CollectableEntity> collectableList;
    List<Entity> inventory;
    private int durability;

    public Armour(String id, Position position) {
        super(id, "armour", position, false);
        this.durability = 5;
    }

    @Override
    public void use(Object o) {
        // setDurability(0);
    }

    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            tmpManager = (GameManager) o;
        }

        player = tmpManager.getPlayer();
        if (player.getPosition().equals(this.getPosition())) {
            collectableList = tmpManager.getCollectableEntity();
            List<CollectableEntity> tmpList = new ArrayList<>();
            boolean haveItem = false;
            for (Entity e : tmpManager.getInventoryList()) {
                if (e.getType().equals(this.getType())) {
                    haveItem = true;
                }
            }

            if (haveItem) {
                return;
            }

            for (CollectableEntity e : collectableList) {
                if (!e.getId().equals(this.getId())) {
                    tmpList.add(e);
                }
            }
            tmpManager.setCollectableEntity(tmpList);
            inventory = tmpManager.getInventoryList();
            inventory.add(this);
        }

    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public void updateDurability(GameManager gameManager) {
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
