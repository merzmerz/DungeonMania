package dungeonmania.CollectableTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.collectable.*;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class UnitTest {
    @Test
    public void armour() {
        Position pos = new Position(0, 0, 2);
        Armour armour = new Armour("0", pos);

        assertEquals(armour.getEntityResponse().getType(), "armour");
        assertEquals(armour.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void arrow() {
        Position pos = new Position(0, 0, 2);
        Arrow arrow = new Arrow("0", pos);

        assertEquals(arrow.getEntityResponse().getType(), "arrow");
        assertEquals(arrow.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void bomb() {
        Position pos = new Position(0, 0, 2);
        Bomb bomb = new Bomb("0", pos);

        assertEquals(bomb.getEntityResponse().getType(), "bomb");
        assertEquals(bomb.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void healthPotion() {
        Position pos = new Position(0, 0, 2);
        HealthPotion potion = new HealthPotion("0", pos);

        assertEquals(potion.getEntityResponse().getType(), "health_potion");
        assertEquals(potion.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void InvincibilityPotion() {
        Position pos = new Position(0, 0, 2);
        InvincibilityPotion potion = new InvincibilityPotion("0", pos);

        assertEquals(potion.getEntityResponse().getType(), "invincibility_potion");
        assertEquals(potion.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void invisibilityPotion() {
        Position pos = new Position(0, 0, 2);
        InvisibilityPotion potion = new InvisibilityPotion("0", pos);

        assertEquals(potion.getEntityResponse().getType(), "invisibility_potion");
        assertEquals(potion.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void key() {
        Position pos = new Position(0, 0, 2);
        Key potion = new Key("0", pos);

        assertEquals(potion.getEntityResponse().getType(), "key");
        assertEquals(potion.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void sword() {
        Position pos = new Position(0, 0, 2);
        Sword sword = new Sword("0", pos);

        assertEquals(sword.getEntityResponse().getType(), "sword");
        assertEquals(sword.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void treasure() {
        Position pos = new Position(0, 0, 2);
        Treasure treasure = new Treasure("0", pos);

        assertEquals(treasure.getEntityResponse().getType(), "treasure");
        assertEquals(treasure.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void wood() {
        Position pos = new Position(0, 0, 2);
        Wood wood = new Wood("0", pos);

        assertEquals(wood.getEntityResponse().getType(), "wood");
        assertEquals(wood.getEntityResponse().getPosition(), pos);

    }

    @Test
    public void testPlayerCollectArmour() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectArmour", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("armour"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("armour")) {
                assertTrue(itemResponse.getType().equals("armour"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectArrow() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectArrow", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("arrow"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("arrow")) {
                assertTrue(itemResponse.getType().equals("arrow"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectBomb() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectBomb", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("bomb"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("bomb")) {
                assertTrue(itemResponse.getType().equals("bomb"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectHealthPotion() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectHealthPotion", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("health_potion"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("health_potion")) {
                assertTrue(itemResponse.getType().equals("health_potion"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectInvincbilityPotion() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectInvicPotion", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("invincibility_potion"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("invincibility_potion")) {
                assertTrue(itemResponse.getType().equals("invincibility_potion"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectInvisibilityPotion() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectInvisPotion", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("invisibility_potion"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("invisibility_potion")) {
                assertTrue(itemResponse.getType().equals("invisibility_potion"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectKey() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectKey", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("key"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("key")) {
                assertTrue(itemResponse.getType().equals("key"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectSword() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectSword", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("sword"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("sword")) {
                assertTrue(itemResponse.getType().equals("sword"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectTreasure() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectTreasure", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("treasure"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("treasure")) {
                assertTrue(itemResponse.getType().equals("treasure"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }

    @Test
    public void testPlayerCollectWood() {

        DungeonManiaController dc = new DungeonManiaController();
        DungeonResponse dungeon = dc.newGame("collectWood", "standard");

        // check entity does not exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            assertFalse(itemResponse.getType().equals("wood"));
        }
        // move player to collect entity
        dungeon = dc.tick(null, Direction.RIGHT);

        // check entity exist inside inventory
        for (ItemResponse itemResponse : dungeon.getInventory()) {
            if (itemResponse.getType().equals("wood")) {
                assertTrue(itemResponse.getType().equals("wood"));
            }
        }
        // when player and collectable entitiy are on the same position, the collectable
        // must disappear from the map
        assertFalse(dungeon.getEntities().stream().filter(o -> o.getType().equals("key")).findFirst().isPresent());

    }
}
