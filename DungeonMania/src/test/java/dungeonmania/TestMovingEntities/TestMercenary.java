package dungeonmania.TestMovingEntities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.CharacterFactory;
import dungeonmania.Entity;
import dungeonmania.Player;
import dungeonmania.MovingEntity.Mercenary.Mercenary;

public class TestMercenary {
    /**
     * Test for Mercenary with MercenaryMoveHostile strategy
     */
    @Test
    void testMercenaryHostile()
    {
        List<Entity> entities = new ArrayList<>();        
        Mercenary mercenary = (Mercenary)CharacterFactory.createEntity("mercenary1", "mercenary", new Position(4, 4));
        Player player = (Player)CharacterFactory.createEntity("player1", "player", new Position(1, 4));
        entities.add(mercenary);
        entities.add(player);
        mercenary.moveMercenary(entities, mercenary.getPosition(), 5, 5);
        assertEquals(new Position(3, 4), mercenary.getPosition());
    }

    /**
     * Test for Mercenary with MercenaryMoveAlly strategy
     */
    @Test
    void testMercenaryAlly()
    {
        List<Entity> entities = new ArrayList<>();        
        Mercenary mercenary = (Mercenary)CharacterFactory.createEntity("mercenary1", "mercenary", new Position(4, 4));
        Player player = (Player)CharacterFactory.createEntity("player1", "player", new Position(1, 4));
        entities.add(mercenary);
        entities.add(player);
        mercenary.setMovingStrategy("ally");
        player.move(Direction.DOWN);
        player.move(Direction.DOWN);
        mercenary.moveMercenary(entities, mercenary.getPosition(), 5, 5);
        assertTrue(player.getPosition().getAdjacentPositions().contains(mercenary.getPosition()));
        player.move(Direction.DOWN);
        mercenary.moveMercenary(entities, mercenary.getPosition(), 5, 5);
        player.move(Direction.DOWN);
        mercenary.moveMercenary(entities, mercenary.getPosition(), 5, 5);
        assertTrue(player.getPosition().getAdjacentPositions().contains(mercenary.getPosition()));
    }

    /**
     * Test for Mercenary with MercenaryMoveScared strategy
     */
    @Test
    void testMercenaryScared()
    {
        List<Entity> entities = new ArrayList<>();        
        Mercenary mercenary = (Mercenary)CharacterFactory.createEntity("mercenary1", "mercenary", new Position(4, 4));
        Player player = (Player)CharacterFactory.createEntity("player1", "player", new Position(1, 4));
        entities.add(mercenary);
        entities.add(player);
        mercenary.setMovingStrategy("scared");
        player.move(Direction.DOWN);
        mercenary.moveMercenary(entities, mercenary.getPosition(), 5, 5);
        assertEquals(new Position(4, 5), mercenary.getPosition());
        player.move(Direction.UP);
        mercenary.moveMercenary(entities, mercenary.getPosition(), 5, 5);
        assertEquals(new Position(4, 4), mercenary.getPosition());
    }
}

