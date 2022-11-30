package dungeonmania.TestMovingEntities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.util.Position;
import dungeonmania.CharacterFactory;
import dungeonmania.MovingEntity.ZombieToast.ZombieToast;

public class TestZombieToast {
    /**
     * Test if zombie actually move away from previos spot in random order.
     */
    @Test
    public void testSpiderSpawnAndMovementBehavior() {
        Position oldPosition;
        ZombieToast zomb = (ZombieToast)CharacterFactory.createEntity("zombie_toast1", "zombie_toast", new Position(4, 4));
        oldPosition = zomb.getEntityResponse().getPosition();
        List<Position> oldPostionAdj = new ArrayList<>();
        oldPostionAdj.addAll(oldPosition.getAdjacentPositions());
        zomb.move(zomb.getPosition());
        assertTrue(oldPostionAdj.contains(zomb.getPosition()));
        oldPosition = zomb.getEntityResponse().getPosition();
        oldPostionAdj = new ArrayList<>();
        oldPostionAdj.addAll(oldPosition.getAdjacentPositions());
        zomb.move(zomb.getPosition());
        assertTrue(oldPostionAdj.contains(zomb.getPosition()));
    }
}
