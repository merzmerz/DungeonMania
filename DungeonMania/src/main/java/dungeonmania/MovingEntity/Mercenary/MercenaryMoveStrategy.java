package dungeonmania.MovingEntity.Mercenary;

import java.util.List;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public abstract class MercenaryMoveStrategy {
    /**
     * Return next position for mercenary, using an instance strategy.
     * @param entities list of entity
     * @param mercenaryPos position of mercenary
     * @param height height of map
     * @param width width of map
     * @return Return next position for mercenary.
     */
    public abstract Position move(List<Entity> entities, Position mercenaryPos, int height, int width);
}
