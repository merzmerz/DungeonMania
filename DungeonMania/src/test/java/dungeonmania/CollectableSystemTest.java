package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

public class CollectableSystemTest {
    
    @Test
    public void testUseHealthPotion() {
        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectHealthPotion", "standard");

        dungeon = dc.tick(null, Direction.RIGHT);

        GameManager gM = dc.getGameManager();
        gM.getPlayer().setHealth(10);

        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("health_potion")) {
                assertTrue(itemResponse.getType().equals("health_potion"));
                dc.tick(itemResponse.getId(), Direction.NONE);
                break;
            }
        }

        assertEquals(gM.getPlayer().getHealth(), 100);
        
    }

    @Test
    public void testUseInvincibilityPotion() {
        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectInvicPotion", "standard");

        dungeon = dc.tick(null, Direction.RIGHT);

        GameManager gM = dc.getGameManager();

        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("invincibility_potion")) {
                assertTrue(itemResponse.getType().equals("invincibility_potion"));
                dc.tick(itemResponse.getId(), Direction.NONE);
                break;
            }
        }
        assertTrue(gM.getPlayer().isInvincible());
        dc.tick(null, Direction.RIGHT);
        dc.tick(null, Direction.RIGHT);
        dc.tick(null, Direction.RIGHT);
        assertFalse(gM.getPlayer().isInvincible());
    }

    @Test
    public void testUseInvisibilityPotion() {
        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectInvisPotion", "standard");

        dungeon = dc.tick(null, Direction.RIGHT);

        GameManager gM = dc.getGameManager();

        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("invisibility_potion")) {
                assertTrue(itemResponse.getType().equals("invisibility_potion"));
                dc.tick(itemResponse.getId(), Direction.NONE);
                break;
            }
        }
        assertTrue(gM.getPlayer().isInvisible());
        dc.tick(null, Direction.RIGHT);
        dc.tick(null, Direction.RIGHT);
        dc.tick(null, Direction.RIGHT);
        assertFalse(gM.getPlayer().isInvisible());
    }

    @Test
    public void testUseBomb() {
        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectBomb", "standard");

        dungeon = dc.tick(null, Direction.RIGHT);
        dungeon = dc.tick(null, Direction.RIGHT);
        dc.getGameManager();
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("bomb")) {
                assertTrue(itemResponse.getType().equals("bomb"));
                dc.tick(itemResponse.getId(), Direction.NONE);
                break;
            }
        }
        dungeon = dc.tick(null, Direction.LEFT);
        for (EntityResponse entityResponse : dungeon.getEntities()) {
            assertFalse(entityResponse.getType().equals("treasure"));
        }
    }
}
