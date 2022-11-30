package dungeonmania.staticentities;

import java.util.List;

import dungeonmania.Entity;
import dungeonmania.GameManager;
import dungeonmania.Observer;
import dungeonmania.MovingEntity.MovingEntity;
import dungeonmania.MovingEntity.ZombieToast.ZombieToast;
import dungeonmania.util.Position;

public class ZombieSpawner extends StaticEntity implements Observer {

    public ZombieSpawner(String id, Position position) {
        super(id, "zombie_toast_spawner", position, true);
        setCanPass(false);
    }


    /**
     * Spawn zombie at the zombie toast spawner and spawn location must be
     * not have any other entities
     * @param game game manager.
     * @return void.
     */
    public void spawnZombie(GameManager game) {
        boolean spawnN = true;
        boolean spawnS = true;
        boolean spawnE = true;
        boolean spawnW = true;
        if ((game.getTick() % 20) == 0) {
            Position spawner_pos = this.getPosition();
            Position adjN = new Position(spawner_pos.getX(), spawner_pos.getY() + 1);
            Position adjS = new Position(spawner_pos.getX(), spawner_pos.getY() - 1);
            Position adjE = new Position(spawner_pos.getX() + 1, spawner_pos.getY());
            Position adjW = new Position(spawner_pos.getX() - 1 , spawner_pos.getY());
            for (Entity entity: game.getEntityList()) {
                if (entity.getPosition().equals(adjN)) {
                    spawnN = false;
                }
                if (entity.getPosition().equals(adjS)) {
                    spawnS = false;
                }
                if (entity.getPosition().equals(adjE)) {
                    spawnE = false;
                }
                if (entity.getPosition().equals(adjW)) {
                    spawnW = false;
                }
            }
            ZombieToast zombie = null;
            if (spawnN == true) {
                zombie = new ZombieToast("zombieToast" + " " + game.getTick(), adjN);
            }
            else if (spawnS == true) {
                zombie = new ZombieToast("zombieToast" + " " + game.getTick(), adjN);
            }
            else if (spawnE == true) {
                zombie = new ZombieToast("zombieToast" + " " + game.getTick(), adjN);
            }
            else if (spawnW == true) {
                zombie = new ZombieToast("zombieToast" + " " + game.getTick(), adjN);
            }
            
            if (zombie != null) {
                List<MovingEntity> movingEntities = game.getMovingEntites();
                movingEntities.add(zombie);
                game.setMovingEntites(movingEntities);

            }  
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        if (o instanceof GameManager) {
            GameManager game = (GameManager) o;  
            spawnZombie(game);
        }
    }



    
}
