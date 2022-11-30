package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class GoalTest {

    @Test
    public void testExitAndSwitch() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("Goaltest1", "");

        DungeonResponse dungeon = dc.tick(null, Direction.NONE);

        assertEquals(dungeon.getGoals(), ":boulder AND :exit");

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(3, 4));
            }
        }

        dungeon = dc.tick(null, Direction.RIGHT);

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(4, 4));
            }

        }

        dungeon = dc.tick(null, Direction.UP);

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(4, 3));
            }

        }
        assertEquals(dungeon.getGoals(), "");
    }

    @Test
    public void testExitBeforeSwitch() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("Goaltest1", "");

        DungeonResponse dungeon = dc.tick(null, Direction.UP);
        dungeon = dc.tick(null, Direction.RIGHT);

        assertEquals(dungeon.getGoals(), ":boulder AND :exit");

        // go to the exit first
        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(4, 3));
            }
        }

        // back to press the switch and back to the exit
        dungeon = dc.tick(null, Direction.LEFT);
        dungeon = dc.tick(null, Direction.DOWN);
        dungeon = dc.tick(null, Direction.RIGHT);
        dungeon = dc.tick(null, Direction.UP);

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(4, 3));
            }

        }
        assertEquals(dungeon.getGoals(), "");
    }

    @Test
    public void testTreasureORExit() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("Goaltest2", "");

        DungeonResponse dungeon = dc.tick(null, Direction.NONE);

        assertEquals(dungeon.getGoals(), ":treasure OR :exit");

        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(3, 4));
            }
        }

        dungeon = dc.tick(null, Direction.RIGHT);
        
        // pick treasure
        for (EntityResponse entity: dungeon.getEntities()) {
            if (entity.getType().equals("player")) {
                assertEquals(entity.getPosition(), new Position(4, 4));
            }

        }
        assertEquals(dungeon.getGoals(), "");
    }
    
}
