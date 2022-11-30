package dungeonmania.MovingEntity.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeonmania.CharacterFactory;
import dungeonmania.Entity;
import dungeonmania.util.Position;

public class SpiderSpawn {
    private int maxAmount;
    private int currAmount;
    private List<Position> grid;

    /**
     * Create new spider spawner.
     * @param entities list of entity
     * @param mapHeight map's height
     * @param mapWidth map's width
     */
    public SpiderSpawn(List<Entity> entities, int mapHeight, int mapWidth) {
        this.maxAmount = 4;
        this.currAmount = 0;
        this.grid = initGrid(entities, mapHeight, mapWidth);
    }

    /**
     * Return grid as list of position
     * @param entities list of entity
     * @param height map's height
     * @param width map's width
     * @return
     */
    private List<Position> initGrid(List<Entity> entities, int height, int width) {
        List<Position> tempGrid = new ArrayList<>(height * width);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempGrid.add(new Position(i, j));
            }
        }
        List<Position> entitiesPos = new ArrayList<>();
        for (Entity en : entities) {
            entitiesPos.add(en.getPosition());
        }
        List<Position> needRemove = new ArrayList<>();
        for (Entity en : entities) {
            if (tempGrid.contains(en.getPosition())) {
                needRemove.add(en.getPosition());
            }
        }
        tempGrid.removeAll(needRemove);
        return tempGrid;
    }

    /**
     * Random spider in random position.
     * @return spider with position in grid that's not wall or boulder
     */
    public Entity randomSpawn() {
        currAmount++;
        Random random = new Random();
        int randNum = random.ints(0, grid.size() - 1).findFirst().getAsInt();
        return CharacterFactory.createEntity("naturalSpawnedSpider: " + currAmount, "spider", this.grid.get(randNum));
    }

    public int getCurrAmount() { return currAmount; }
    public int getMaxAmount() { return maxAmount; }
}
