package dungeonmania.TestMovingEntities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.staticentities.Boulder;
import dungeonmania.util.Position;
import dungeonmania.CharacterFactory;
import dungeonmania.Entity;
import dungeonmania.MovingEntity.Spider.Spider;

public class TestSpider {
    /**
     * Test for spider moving right
     */
    @Test
    public void testspiderRight() {
            Spider spider = (Spider)CharacterFactory.createEntity("spider1", "spider", new Position(4, 4));
            spider.move(spider.getPosition());
            assertEquals(new Position(4, 3), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(5, 3), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(5, 4), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(5, 5), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(4, 5), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(3, 5), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(3, 4), spider.getEntityResponse().getPosition());
            spider.move(spider.getPosition());
            assertEquals(new Position(3, 3), spider.getEntityResponse().getPosition());
    }

    /**
     * Test if spider change strategy after hit the boulder.
     */
    @Test
    public void testspiderLeftAndBoulder() {
        List<Entity> entities = new ArrayList<>();
        Spider spider = (Spider)CharacterFactory.createEntity("spider1", "spider", new Position(4, 4));
        Boulder boulder = (Boulder)CharacterFactory.createEntity("boulder", "boulder", new Position(5, 3));
        entities.add(spider);
        entities.add(boulder);
        spider.move(spider.getPosition());
        assertEquals(new Position(4, 3), spider.getEntityResponse().getPosition());
        for (int i = 0; i < 2; i++) {
            if (!canPassForSpider(spider.move(spider.getPosition()), entities)) {
                spider.setMovingStrategy("");
                spider.move(spider.getPosition());
                assertEquals(new Position(4 - i, 3), spider.getEntityResponse().getPosition());
            }
        }
    }

    /**
     * Return boolean indicate if newPos can be pass by entity or not
     * @param newPos
     * @param entities
     * @return boolean indicate if newPos can be pass by entity or not
     */
    private boolean canPassForSpider(Position newPos, List<Entity> entities) {   
        for (Entity entity : entities) {
            if (entity.getType().equals("boulder") && entity.getPosition().equals(newPos)) {
                return false;
            }
        }
        return true;
    }
}