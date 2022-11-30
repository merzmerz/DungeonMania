package dungeonmania.MovingEntity.Mercenary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Dijkstra {
    private int height;
    private int width;
    private Position source;
    private Map<Position, Position> prev = new HashMap<>();
    private Map<Position, Double> dist = new HashMap<>();

    /**
     * Set up height, width, and source for further compution.
     * @param height height of map
     * @param width width of map
     * @param source position of user
     */
    public Dijkstra(int height, int width, Position source) { 
        this.height = height;
        this.width = width;
        this.source = source.asLayer(0);
    }

    /**
     * Compute prev array for further use in getPath.
     * @return previos node of each node in form of map
     */
    public Map<Position, Position> dijkstra() {
        List<Position> grid = initGrid(this.height, this.width);

        for (Position p : grid) {
            dist.put(p, Double.POSITIVE_INFINITY);
            prev.put(p, null);
        }
        dist.put(this.source, 0.0);

        Queue<Position> q = new LinkedList<>();
        for (Position pos : grid) {
            q.add(pos);
        }
        
        while(!q.isEmpty()) {
            Position u = new Position(-1, -1);
            u = getLowestDistInQueue(q);
            Position v = null;
            for (int i = 0; i <= 3; i++) {
                if (i == 0) v = u.translateBy(Direction.UP); 
                if (i == 1) v = u.translateBy(Direction.DOWN); 
                if (i == 2) v = u.translateBy(Direction.LEFT); 
                if (i == 3) v = u.translateBy(Direction.RIGHT); 
                v = v.asLayer(0);
                if (dist.get(v) == (null)) continue;
                if ((dist.get(u) + 1.0 < dist.get(v))) {
                    dist.put(v, dist.get(u) + 1.0);

                    prev.put(v, u);
                }
            }
            q.remove(u);
        }
        return prev;
    }

    /**
     * Get path from source to target as list of position.
     * @param target destination that source need to approach
     * @return path from source to list in correct order
     */
    public List<Position> getPath(Position target) {
        List<Position> path = new ArrayList<>();
        Position step = target;

        if (prev.get(step) == null) {
            return null;
        }
        path.add(step);

        while (prev.get(step) != null) {
            step = prev.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Initiate grid of positions.
     * @param height height of map
     * @param width width of map
     * @return grid in form of list of positions
     */
    private List<Position> initGrid(int height, int width) {
        List<Position> grid = new ArrayList<>(height * width);
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                grid.add(new Position(i, j));
            }
        }
        return grid;
    }

    /**
     * Check for lowest distance in dist map and check again in queue to get
     * the lowest distance that still in queue.
     * @param q targeted queue
     * @return lowest distance in queue
     */
    Position getLowestDistInQueue(Queue<Position> q) {
        Double minDist = Double.POSITIVE_INFINITY;
        for (Map.Entry<Position, Double> entry : dist.entrySet()) {
                if (entry.getValue() < minDist && q.contains(entry.getKey())) {
                    return entry.getKey();
                }
        }
        return null;
    }
}
