package dungeonmania.collectable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Player;
import dungeonmania.MovingEntity.MovingEntity;
import dungeonmania.staticentities.FloorSwitch;
import dungeonmania.staticentities.StaticEntity;
import dungeonmania.util.Position;

public class Bomb extends CollectableEntity {
    Player player;
    GameManager tmpManager;
    List<CollectableEntity> collectableList;
    List<Entity> inventory;

    public Bomb(String id, Position position) {
        super(id, "bomb", position, false);
    }

    @Override
    public void use(Object o) {
        if (o instanceof GameManager) {
            tmpManager = (GameManager) o;
        }

        if (!tmpManager.getItemUsedId().equals(this.getId())) {
            return;
        }

        List<Entity> tmpList = new ArrayList<>();
        for (Entity e : tmpManager.getInventoryList()) {
            if (!e.getId().equals(this.getId())) {
                tmpList.add(e);
            }
        }
        tmpManager.setInventoryList(tmpList);
        List<CollectableEntity> collectableEntities = tmpManager.getCollectableEntity();
        collectableEntities.add(new Bomb(this.getId(), player.getPosition()));
        
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

        for (Entity e : tmpManager.getEntityList()) {
            if (e.getType().equals("switch") && e.getPosition().isAdjacent(e.getPosition(), this.getPosition())) {
                FloorSwitch s = (FloorSwitch) e;
                if (s.getTrigger()) {
                    List<Position> targetPos = new ArrayList<>(this.getPosition().getAdjacentPositions());

                    List<StaticEntity> tmpStatic = new ArrayList<>();
                    for (StaticEntity staticEn : tmpManager.getStaticEntity()) {
                        if (targetPos.contains(staticEn.getPosition())) {
                        } else {
                            tmpStatic.add(staticEn);
                        }
                    }

                    List<CollectableEntity> tmpCollectable = new ArrayList<>();
                    for (CollectableEntity collectableEn : tmpManager.getCollectableEntity()) {
                        if (targetPos.contains(collectableEn.getPosition()) || collectableEn.getId().equals(this.getId())) {
                        } else {
                            tmpCollectable.add(collectableEn);
                        }
                    }

                    List<MovingEntity> tmpMoving = new ArrayList<>();
                    for (MovingEntity movingEn : tmpManager.getMovingEntites()) {
                        if (targetPos.contains(movingEn.getPosition())) {
                        } else {
                            tmpMoving.add(movingEn);
                        }
                    }

                    tmpManager.setStaticEntity(tmpStatic);
                    tmpManager.setCollectableEntity(tmpCollectable);
                    tmpManager.setMovingEntites(tmpMoving);
                }
            }
        }
    }
}
