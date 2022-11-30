package dungeonmania.MovingEntity.Mercenary;

import java.util.List;

import dungeonmania.Entity;
import dungeonmania.Player;
import dungeonmania.util.Position;

public class MercenaryMoveAlly extends MercenaryMoveStrategy {
    public MercenaryMoveAlly() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position move(List<Entity> entities, Position mercenaryPos, int height, int width) {
        Player player = null;
        for (Entity en : entities) {
            if (en.getType().equals("player")) {
                player = (Player)en;
            }
        }
        List<Entity> playerHistory = player.getEntityHistory();
        if (playerHistory.size() < 1) return player.getPosition();
        return playerHistory.get(playerHistory.size() - 1).getPosition();
    }
}
