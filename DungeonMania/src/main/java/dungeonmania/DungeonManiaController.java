package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.staticentities.StaticEntity;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DungeonManiaController {
    GameManager gameManager;
    List<String> collectableType = Arrays.asList("armour","arrow","bomb","health_potion","invincibility_potion","invisibility_potion","key","sword","treasure","wood");

    public DungeonManiaController() {
    }

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    public List<String> getGameModes() {
        return Arrays.asList("Standard", "Peaceful", "Hard");
    }

    public static List<String> dungeons() {
        try {
            return FileLoader.listFileNamesInResourceDirectory("/dungeons");
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public DungeonResponse newGame(String dungeonName, String gameMode) throws IllegalArgumentException {
        String JSONstr = null;
        try {
            JSONstr = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");

        } catch (IOException e) {} 

        JSONObject obj = new JSONObject(JSONstr);
        int width = obj.getInt("width");
        int height = obj.getInt("height");

        List<Entity> entities = new ArrayList<>();
        JSONArray arr = obj.getJSONArray("entities");
        
        for (int i = 0; i < arr.length(); i++) {
            JSONObject curr = arr.getJSONObject(i);
            int coor_x = curr.getInt("x");
            int coor_y = curr.getInt("y");
            String type = curr.getString("type");
            
            entities.add(CharacterFactory.createEntity(Integer.toString(i), type, new Position(coor_x, coor_y, 0)));
        }
        
        gameManager = new GameManager("dungeon-1", dungeonName, entities, "goal", height, width);
        return gameManager.getDungeonResponse();
        }
        
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        return null;
    }

    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        return null;
    }

    public List<String> allGames() {
        return new ArrayList<>();
    }

    public DungeonResponse tick(String itemUsed, Direction movementDirection) throws IllegalArgumentException, InvalidActionException {
        return gameManager.updateEntities(itemUsed, movementDirection);
    }

    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return gameManager.interact(entityId);
    }

    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return gameManager.buildItem(buildable);
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public List<EntityResponse> buildDungeon(int length, int height) {
        List<EntityResponse> walls = new ArrayList<>();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 || y == 0 || x == 10 || y == 8) {
                    // only generate borders
                    walls.add(new EntityResponse("entity-" + x + " - " + y, "wall", new Position(x, y, 2), false));
                }
            }
        }
        return walls;
    }

    public void checkType(Entity entity) {
        if (entity instanceof StaticEntity) {

        }
    }
}
