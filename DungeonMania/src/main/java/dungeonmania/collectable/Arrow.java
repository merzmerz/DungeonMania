package dungeonmania.collectable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Player;
import dungeonmania.util.Position;

public class Arrow extends CollectableEntity {

    Player player;
    GameManager tmpManager;
    List<CollectableEntity> collectableList;
    List<Entity> inventory;

    public Arrow(String id, Position position) {
        super(id, "arrow", position, false);
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
