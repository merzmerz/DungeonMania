package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.MovingEntity.*;
import dungeonmania.buildable.Shield;
import dungeonmania.buildable.Bow;
import dungeonmania.collectable.*;

public class BattleManager {
    private GameManager game;
    private Player player;
    private final List<MovingEntity> enemies;

    public BattleManager(GameManager g) {
        this.game = g;
        this.player = g.getPlayer();
        this.enemies = g.getMovingEntites();
    } 

    public void fight() {
        List<MovingEntity> deadEnemies = new ArrayList<>();
        
        for (MovingEntity enemy : enemies) {
            if (!player.getPosition().equals(enemy.getPosition())) {
                continue;
            }

            while (player.getHealth() > 0 && enemy.getHealth() > 0) {
                int pHealth = player.getHealth();
                int pAtk = player.getAttackDamage();
                int eHealth = enemy.getHealth();
                int eAtk = enemy.getAtkDamage();
                List<Entity> inventory = new ArrayList<>();
                inventory = game.getInventoryList();

                for (Entity equip : inventory) {
                    if (equip instanceof Armour) {
                        eAtk = eAtk/2;
                        ((Armour) equip).setDurability(((Armour) equip).getDurability() - 1);
                    }
                    if (equip instanceof Sword) {
                        pAtk = pAtk + 30;
                        ((Sword) equip).setDurability(((Sword) equip).getDurability() - 1);
                    }
                    if (equip instanceof Bow) {
                        pAtk = pAtk * 2;
                        ((Bow) equip).setDurability(((Bow) equip).getDurability() - 1);
                    }
                    if (equip instanceof Shield) {
                        eAtk = eAtk - 5;
                        ((Shield) equip).setDurability(((Shield) equip).getDurability() - 1);
                    }
                }

                if (player.isInvincible()) {
                    enemy.setHealth(0);
                } else {
                    enemy.setHealth(eHealth - ((pHealth * pAtk)/5));
                }
                if (enemy.getHealth() <= 0) {
                    deadEnemies.add(enemy);
                }
                player.setHealth(pHealth - ((eHealth * eAtk)/10)); 
            }
        }

        for (MovingEntity e : deadEnemies) {
            enemies.remove(e);
        }
        game.setMovingEntites(enemies);
    }

    public boolean playerIsDeath() {
        if (player.getHealth() <= 0) {
            return true;
        } 
        return false;
    }
}
