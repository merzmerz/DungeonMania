package dungeonmania.MovingEntity.Mercenary;

import dungeonmania.Entity;
import dungeonmania.MovingEntity.MovingEntity;
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mercenary extends MovingEntity {
    private int bribedAmount = 1;
    private boolean isBribed = false;
    private MercenaryMoveStrategy mercenaryMoveStrategy = new MercenaryMoveHostile();

    /**
     * Set up mercenary.
     * @param id Take mercenary's id.
     * @param position Take mercenary's position.
     */
    public Mercenary(String id, Position position) {
        super(id, "mercenary", position, true);
        setAtkDamage(10);
        setHealth(80);
        setCanPass(true);
    }

    /**
     * Return next position for mercenary, using an instance strategy.
     * @param entities list of entity
     * @param mercenaryPos position of mercenary
     * @param height height of map
     * @param width width of map
     * @return Return next position for mercenary.
     */
    public Position moveMercenary(List<Entity> entities, Position mercenaryPos, int height, int width) {
        Position newPos  = mercenaryMoveStrategy.move(entities, mercenaryPos, height, width);
        this.setPosition(newPos);
        return newPos;
    }


    /**
     * Return position mercenary position
     * @param playerPosition player position
     * @param entities list of entity
     * @param height height of map
     * @param width width of map
     * @return position mercenary position
     */
    public static Position spawnAroundEntry(Position playerPosition, List<Entity> entities, int height, int width) {
        List<Position> tempGrid = new ArrayList<>(height * width);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempGrid.add(new Position(i, j));
            }
        }
        List<Position> entitiesPos = new ArrayList<>();
        for (Entity en : entities) {
            entitiesPos.add(en.getPosition());
        }
        List<Position> needRemove = new ArrayList<>();
        for (Entity en : entities) {
            if (tempGrid.contains(en.getPosition())) {
                needRemove.add(en.getPosition());
            }
        }
        tempGrid.removeAll(needRemove);
        
        List<List<Position>> doubleLayerAdj = new ArrayList<>();
        List<Position> adjPlayer = playerPosition.getAdjacentPositions();
        for (int i = 0; i < 8; i++) {
            doubleLayerAdj.add(adjPlayer.get(i).getAdjacentPositions());
        }

        Random random = new Random();
        int randNum = random.ints(0, doubleLayerAdj.size() - 1).findFirst().getAsInt();

        Position newPos = playerPosition;
        for (Position pos : doubleLayerAdj.get(randNum)) {
            if (tempGrid.contains(pos)) {
                newPos = pos;
            }
        }
        System.out.println(newPos);
        return newPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position move(Position entityPos) {
        return null;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMovingStrategy(String strat) {
        if (strat.equals("scared")) {
            mercenaryMoveStrategy = new MercenaryMoveScared();
        } else if (strat.equals("ally")) {
            isBribed = true;
            mercenaryMoveStrategy = new MercenaryMoveAlly();
        } else if (strat.equals("hostile")) {
            mercenaryMoveStrategy = new MercenaryMoveHostile();
        }
    }

    public boolean getIsBribed() { return this.isBribed; }
    public void setIsBribed(boolean isBribed) { this.isBribed =  isBribed; }

    public int getBribedAmount() { return bribedAmount; }
    public void setBribedAmount(int bribedAmount) { this.bribedAmount = bribedAmount; }

    public MercenaryMoveStrategy getMercenaryMoveStrategy() { return mercenaryMoveStrategy; }
}
