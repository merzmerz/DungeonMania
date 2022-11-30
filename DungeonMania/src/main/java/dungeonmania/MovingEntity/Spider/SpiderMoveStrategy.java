package dungeonmania.MovingEntity.Spider;

import dungeonmania.util.Position;

public abstract class SpiderMoveStrategy {
    /**
     * Return spider's new position, compute using spider's current moving strategy.
     * @param entityPos entity position
     * @param origiPosition origin position
     * @return spider's new position, after compute using spider's current moving strategy
     * @apiNote Adjacent
     * @apiNote 0 1 2
     * @apiNote 7 p 3
     * @apiNote 6 5 4
     */
    public abstract Position spiderMove(Position entityPos, Position origiPosition);
}
