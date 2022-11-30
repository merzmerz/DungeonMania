package dungeonmania.collectable;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Player;
import dungeonmania.util.Position;

public class OneRing extends CollectableEntity{
    Player player;
    GameManager tmpManager;
    List<CollectableEntity> collectableList;
    List<Entity> inventory;

    public OneRing(String id, Position position) {
        super(id, "one_ring", position, false);
    }

    @Override
    public void use(Object o) {

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
}
