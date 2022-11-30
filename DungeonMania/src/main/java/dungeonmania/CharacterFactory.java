package dungeonmania;

import dungeonmania.staticentities.*;
import dungeonmania.collectable.*;
import dungeonmania.MovingEntity.Mercenary.Mercenary;
import dungeonmania.MovingEntity.Spider.Spider;
import dungeonmania.MovingEntity.ZombieToast.ZombieToast;
import dungeonmania.util.Position;


public class CharacterFactory {

    public static Entity createEntity(String id, String type, Position position) {

        if (type.equals("wall")) {
            return new Wall(id, position);
        } else if (type.equals("player")) {
            return new Player(100, 10, id, position);
        } else if (type.equals("boulder")) {
            return new Boulder(id, position);
        } else if (type.equals("portal")) {
            return new Portal(id, position);
        } else if (type.equals("exit")) {
            return new Exit(id, position);
        } else if (type.equals("switch")) {
            return new FloorSwitch(id, position);
        } else if (type.equals("armour")) {
            return new Armour(id, position);
        } else if (type.equals("arrow")) {
            return new Arrow(id, position);
        } else if (type.equals("bomb")) {
            return new Bomb(id, position);
        } else if (type.equals("health_potion")) {
            return new HealthPotion(id, position);
        } else if (type.equals("invincibility_potion")) {
            return new InvincibilityPotion(id, position);
        } else if (type.equals("invisibility_potion")) {
            return new InvisibilityPotion(id, position);
        } else if (type.equals("key")) {
            return new Key(id, position);
        } else if (type.equals("sword")) {
            return new Sword(id, position);
        } else if (type.equals("treasure")) {
            return new Treasure(id, position);
        } else if (type.equals("wood")) {
            return new Wood(id, position);
        } else if (type.equals("spider")) {
            return new Spider(id, position);
        } else if (type.equals("mercenary")) {
            return new Mercenary(id, position);
        } else if (type.equals("zombie_toast")) {
            return new ZombieToast(id, position);
        } else if (type.equals("zombie_toast_spawner")) {
            return new ZombieSpawner(id, position);
        } else if (type.equals("door")) {
            return new Door(id, position);
        }
        
        return null;


    }
}
