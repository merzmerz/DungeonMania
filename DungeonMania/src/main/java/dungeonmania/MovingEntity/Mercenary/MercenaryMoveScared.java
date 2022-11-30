package dungeonmania.MovingEntity.Mercenary;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.Player;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class MercenaryMoveScared extends MercenaryMoveStrategy {

    public MercenaryMoveScared() { }

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
        if (player.getMovement().equals(Direction.UP)) {
            Position newPosMercenary = mercenaryPos.translateBy(Direction.UP);
            for (Entity en : entities) {
                if (en.getPosition().equals(newPosMercenary) && (en.getType().equals("wall") || en.getType().equals("boulder"))) {
                    return tryMoveAdj(mercenaryPos.getAdjacentPositions(), entities, mercenaryPos);
                }
            }
            return newPosMercenary;
        } else if (player.getMovement().equals(Direction.DOWN)) {
            Position newPosMercenary = mercenaryPos.translateBy(Direction.DOWN);
            for (Entity en : entities) {
                if (en.getPosition().equals(newPosMercenary) && (en.getType().equals("wall") || en.getType().equals("boulder"))) {
                    return tryMoveAdj(mercenaryPos.getAdjacentPositions(), entities, mercenaryPos);
                } 
            }
            return newPosMercenary;
        } else if (player.getMovement().equals(Direction.LEFT)) {
            Position newPosMercenary = mercenaryPos.translateBy(Direction.LEFT);
            for (Entity en : entities) {
                if (en.getPosition().equals(newPosMercenary) && (en.getType().equals("wall") || en.getType().equals("boulder"))) {
                    return tryMoveAdj(mercenaryPos.getAdjacentPositions(), entities, mercenaryPos);
                } 
            }
            return newPosMercenary;
        } else if (player.getMovement().equals(Direction.RIGHT)) {
            Position newPosMercenary = mercenaryPos.translateBy(Direction.RIGHT);
            for (Entity en : entities) {
                if (en.getPosition().equals(newPosMercenary) && (en.getType().equals("wall") || en.getType().equals("boulder"))) {
                    return tryMoveAdj(mercenaryPos.getAdjacentPositions(), entities, mercenaryPos);
                }
            }
            return newPosMercenary;
        }
        return tryMoveAdj(mercenaryPos.getAdjacentPositions(), entities, mercenaryPos);
    }

    /**
     * Return selected position in list of mercenary adjacent position.
     * @param adjMercenary  list of mercenary's adjacent position
     * @param entities list of entity
     * @param mercenaryPos position of mercenary
     * @return position that mercenary will not be hit by wall or boulder
     */
    private Position tryMoveAdj(List<Position> adjMercenary, List<Entity> entities, Position mercenaryPos) {
        Position posList[] = {adjMercenary.get(1), adjMercenary.get(3), adjMercenary.get(5), adjMercenary.get(7)};
        List<Position> container = new ArrayList<>();
        for (Entity en : entities) {
            if (en.getPosition().equals(adjMercenary.get(1))) {
                container.add(posList[0]);
            } else if (en.getPosition().equals(adjMercenary.get(3))) {
                container.add(posList[1]);
            } else if (en.getPosition().equals(adjMercenary.get(5))) {
                container.add(posList[2]);
            } else if (en.getPosition().equals(adjMercenary.get(7))) {
                container.add(posList[3]);
            }
        }

        if (!container.contains(posList[0])) {
            return posList[0];
        } else if (!container.contains(posList[1])) {
            return posList[1];
        } else if (!container.contains(posList[2])) {
            return posList[2];
        } else if (!container.contains(posList[3])) {
            return posList[3];
        }
        return mercenaryPos;
    }   
}
