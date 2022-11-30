package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import dungeonmania.collectable.Armour;
import dungeonmania.collectable.Sword;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class BattleManagerTest {
    @Test
    public void test_one_spider_win() {
        List<Entity> entities = new ArrayList<>();
        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);
        game.updateEntities(null, Direction.UP);
        BattleManager battle = new BattleManager(game);
        battle.fight();
        assertFalse(battle.playerIsDeath());
    }

    @Test
    public void test_three_spider_win() {
        List<Entity> entities = new ArrayList<>();

        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertFalse(battle.playerIsDeath());
    }

    @Test
    public void test_four_spider_lose() {
        List<Entity> entities = new ArrayList<>();


        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(5), "spider", new Position(0, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertTrue(battle.playerIsDeath());
    }

    @Test
    public void test_one_spider_sword_win() {
        List<Entity> entities = new ArrayList<>();


        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "sword", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertFalse(battle.playerIsDeath());
    }

    @Test
    public void test_four_spider_sword_lose() {
        List<Entity> entities = new ArrayList<>();


        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(5), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(6), "sword", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertTrue(battle.playerIsDeath());
    }

    @Test
    public void test_three_spider_armour_win() {
        List<Entity> entities = new ArrayList<>();

        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(5), "armour", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertFalse(battle.playerIsDeath());
    }

    @Test
    public void test_four_spider_armour_win() {
        List<Entity> entities = new ArrayList<>();

        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(5), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(6), "armour", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);
        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertFalse(battle.playerIsDeath());
    }

    @Test
    public void test_four_spider_armour_sword_win() {
        List<Entity> entities = new ArrayList<>();

        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(5), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(6), "armour", new Position(1, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(7), "sword", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        assertFalse(battle.playerIsDeath());
    }

    @Test
    public void test_durability() {
        List<Entity> entities = new ArrayList<>();

        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "armour", new Position(1, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "sword", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        for (Entity equip : game.getInventoryList()) {
            if (equip instanceof Armour) {
                assertEquals(((Armour) equip).getDurability(), 4);
            }
            if (equip instanceof Sword) {
                assertEquals(((Sword) equip).getDurability(), 4);
            }
        }

        
    }

    @Test
    public void test_durability_zero() {
        List<Entity> entities = new ArrayList<>();

        entities.add(CharacterFactory.createEntity(Integer.toString(1), "player", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(2), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(3), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(4), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(5), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(6), "spider", new Position(0, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(7), "armour", new Position(1, 0, 2)));
        entities.add(CharacterFactory.createEntity(Integer.toString(8), "sword", new Position(1, 0, 2)));

        GameManager game = new GameManager("dungeonId", "testSpider", entities, "goals", 5, 5);

        game.updateEntities(null, Direction.RIGHT);

        game.updateEntities(null, Direction.UP);

        BattleManager battle = new BattleManager(game);
    
        battle.fight();

        for (Entity equip : game.getInventoryList()) {
            if (equip instanceof Armour) {
                assertEquals(((Armour) equip).getDurability(), 0);
            }
            if (equip instanceof Sword) {
                assertEquals(((Sword) equip).getDurability(), 0);
            }
        }

    }
}
