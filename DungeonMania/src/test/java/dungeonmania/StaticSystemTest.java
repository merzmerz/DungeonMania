package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;


public class StaticSystemTest {

    @Test
    public void testBoulderAndSwitch() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("switchtest", "");

        DungeonResponse dungeon = dc.tick(null, Direction.RIGHT);

        assertEquals(dungeon.getGoals(), ":boulder");

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(1, 0));
            }
            else if (entity.getType().equals("boulder")) {
                assertEquals(entity.getPosition(),  new Position(2, 0));
            }
            else if (entity.getType().equals("switch")) {
                assertEquals(entity.getPosition(),  new Position(3, 0));
            }
        }

        dungeon = dc.tick(null, Direction.RIGHT);
        assertEquals(dungeon.getGoals(), "");
        
        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(2, 0));
            }
            else if (entity.getType().equals("boulder")) {
                assertEquals(entity.getPosition(),  new Position(3, 0));
            }
            else if (entity.getType().equals("switch")) {
                assertEquals(entity.getPosition(),  new Position(3, 0));
            }
        }  
    }

    @Test
    public void testPortal() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("portals", "");

        DungeonResponse dungeon = dc.tick(null, Direction.RIGHT);

        // player starts at 0,0 and move into 1,0 portal => 4,0 portal 
        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(5, 0));
            }
            
        }
    }

    @Test
    public void testDoorandKey() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("doorAndKey", "");

        DungeonResponse dungeon = dc.tick(null, Direction.RIGHT);

        for (EntityResponse entity: dungeon.getEntities()) {
            // cant move through door
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(2, 2, 2));
            }
        }
        // pick up the key and move to the door again
        dungeon = dc.tick(null, Direction.UP);
        dungeon = dc.tick(null, Direction.DOWN);
        dungeon = dc.tick(null, Direction.RIGHT);

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(3, 2, 2));
            }
        } 
    }

    @Test
    public void testExitAndSpawner() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("ExitAndSpawner", "");


        DungeonResponse dungeon = dc.tick(null, Direction.RIGHT);
        assertEquals(dungeon.getGoals(), ":exit");
        for (int i = 0 ; i < 21; i++) {
            dungeon = dc.tick(null, Direction.NONE);
        }

        // After 20 ticks spawn zombie
        for (EntityResponse entity: dungeon.getEntities()) {
            // cant move through door
            if (entity.getType().equals("zombie_toast_spawner")) {
                assertEquals(entity.getType(), "zombie_toast_spawner");
            }
        }
        dungeon = dc.tick(null, Direction.RIGHT);
        dungeon = dc.tick(null, Direction.RIGHT);
        dungeon = dc.tick(null, Direction.RIGHT);

        assertEquals(dungeon.getGoals(), "");
    }
}
