package dungeonmania.StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import dungeonmania.staticentities.*;
import dungeonmania.util.Position;

public class UnitTest {
    @Test
    public void Wall() {
        Position position = new Position(0, 0, 2);
        Wall new_wall = new Wall("wallid", position);
        // check wall attributes
        assertEquals(new_wall.getPosition(), position);
        assertEquals(new_wall.getIsInteractable(), false);
        assertEquals(new_wall.getcanPass(), false);
    }

    @Test
    public void Exit() {
        Position exit_pos = new Position(5, 5, 0);
        Exit exit = new Exit("exitid", exit_pos);
        // check exit attributes
        assertEquals(exit.getPosition(), exit_pos);
        assertEquals(exit.getIsInteractable(), false);
        assertEquals(exit.getcanPass(), true);

    }

    @Test
    public void Boulder() {
        Position boulder_pos = new Position(5, 5, 2);
        Boulder boulder = new Boulder("boulderid", boulder_pos);

        assertEquals(boulder.getPosition(), boulder_pos);
        assertEquals(boulder.getIsInteractable(), false);
        assertEquals(boulder.getcanPass(), false);
        
    }

    @Test
    public void Switch() {
        Position switch_pos = new Position(2,2,0);
        FloorSwitch floor_switch = new FloorSwitch("switchid", switch_pos);
        // check attributes
        assertEquals(floor_switch.getPosition(), switch_pos);
        assertEquals(floor_switch.getIsInteractable(), false);
        assertEquals(floor_switch.getcanPass(), true);
        
    }

    @Test
    public void Door() {
        Door door = new Door("door-id", new Position(2, 2, 2));
        assertEquals(door.getPosition(), new Position(2, 2, 2));
        assertEquals(door.getIsInteractable(), false);
        assertEquals(door.getcanPass(), false);
        
    }

    @Test
    public void Portal() {
        Portal portal = new Portal("portalid", new Position(2, 2, 2));
        assertEquals(portal.getPosition(), new Position(2, 2, 2));
        assertEquals(portal.getIsInteractable(), false);
        assertEquals(portal.getcanPass(), true);
    }

    @Test
    public void Zomebie_Toast_Spawner() {
        ZombieSpawner spawner = new ZombieSpawner("spawnid", new Position(2, 2, 2));
        assertEquals(spawner.getPosition(), new Position(2, 2, 2));
        assertEquals(spawner.getIsInteractable(), true);
        assertEquals(spawner.getcanPass(), false);
        
    }

    
    
}
