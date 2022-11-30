package dungeonmania.MovingEntity.Spider;

import dungeonmania.MovingEntity.MovingEntity;
import dungeonmania.util.Position;

public class Spider extends MovingEntity {
    private Position originalPosition;
    private SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveRight();

    /**
     * Set up spider.
     * @param id spider's id.
     * @param position spider's position.
     */
    public Spider(String id, Position position) {
        super(id, "spider", position, false);
        setAtkDamage(5);
        setHealth(50);
        setCanPass(true);
        this.originalPosition = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position move(Position entityPos) {
        Position newSpiderPos = spiderMoveStrategy.spiderMove(entityPos, originalPosition);
        setPosition(newSpiderPos);
        return newSpiderPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMovingStrategy(String strat) {
        if (this.spiderMoveStrategy instanceof SpiderMoveLeft) {
            this.spiderMoveStrategy = new SpiderMoveRight();
        } else if (this.spiderMoveStrategy instanceof SpiderMoveRight) {
            this.spiderMoveStrategy = new SpiderMoveLeft();
        }
    }
}
