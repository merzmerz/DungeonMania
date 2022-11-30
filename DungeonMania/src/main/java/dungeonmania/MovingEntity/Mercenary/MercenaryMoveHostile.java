package dungeonmania.MovingEntity.Mercenary;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.util.Position;

public class MercenaryMoveHostile extends MercenaryMoveStrategy {
    public MercenaryMoveHostile() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position move(List<Entity> entities, Position mercenaryPos, int height, int width)
    {
        Dijkstra dijkstra = new Dijkstra(height, width, mercenaryPos);
        dijkstra.dijkstra();
        List<Position> shortestPath = dijkstra.getPath(getPlayerPos(entities));
        Position newStep;
        System.out.println("shortessspathhh " + shortestPath);
        if (shortestPath == null) {
            return mercenaryPos;
        }
        newStep = shortestPath.get(1);
        
        List<Position> mercenaryAdj = mercenaryPos.getAdjacentPositions();
        for (Entity en : entities) {
            if (en.getPosition().equals(newStep)) {
                if (en.getType().equals("wall") || en.getType().equals("boulder")) {
                    return tryMoveAdj(mercenaryAdj, entities, mercenaryPos);
                }
            }
        }
        return newStep;
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

    /**
     * Return player's position.
     * @param entities list of entity
     * @return position of player
     */
    private Position getPlayerPos(List<Entity> entities) {
        for (Entity en : entities) {
            if (en.getType().equals("player")) {
                return en.getPosition();
            }
        }
        return null;
    }
}
