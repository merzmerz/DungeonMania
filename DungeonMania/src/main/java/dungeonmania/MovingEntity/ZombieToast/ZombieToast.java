package dungeonmania.MovingEntity.ZombieToast;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import dungeonmania.MovingEntity.MovingEntity;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends MovingEntity {
    private Position posFromLastTick;

    /**
     * Set up zombieToast.
     * @param id zombieToast's id.
     * @param position zombieToast's position.
     */
    public ZombieToast(String id, Position position) {
        super(id, "zombie_toast", position, false);
        setAtkDamage(8);
        setHealth(100);
        setCanPass(true);
        this.posFromLastTick = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position move(Position entityPos) {
        List<Position> cardinalAdj = Arrays.asList(entityPos.translateBy(Direction.UP), entityPos.translateBy(Direction.DOWN), entityPos.translateBy(Direction.LEFT), entityPos.translateBy(Direction.RIGHT));
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        setPosition(cardinalAdj.get(randomNum));
        return cardinalAdj.get(randomNum);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMovingStrategy(String strat) { }

    public void setPosFromLastTick(Position posFromLastTick) { this.posFromLastTick = posFromLastTick; }
    public Position getPosFromLastTick() { return posFromLastTick; }
}
