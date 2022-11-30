package dungeonmania.MovingEntity.Spider;

import java.util.List;

import dungeonmania.util.Position;
import dungeonmania.util.Direction;

public class SpiderMoveRight extends SpiderMoveStrategy {
    public SpiderMoveRight() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position spiderMove(Position spiderPosition, Position originalPosition) {
        List<Position> adjOriginPos = originalPosition.getAdjacentPositions();
        if (spiderPosition.equals(originalPosition)){
            return spiderPosition.translateBy(Direction.UP);
        } else if (spiderPosition.equals(adjOriginPos.get(1))){
            return spiderPosition.translateBy(Direction.RIGHT);
        } else if (spiderPosition.equals(adjOriginPos.get(2))){
            return spiderPosition.translateBy(Direction.DOWN);
        } else if (spiderPosition.equals(adjOriginPos.get(3))){
            return spiderPosition.translateBy(Direction.DOWN);
        } else if (spiderPosition.equals(adjOriginPos.get(4))){
            return spiderPosition.translateBy(Direction.LEFT);
        }else if (spiderPosition.equals(adjOriginPos.get(5))){
            return spiderPosition.translateBy(Direction.LEFT);
        }else if (spiderPosition.equals(adjOriginPos.get(6))){
            return spiderPosition.translateBy(Direction.UP);
        }else if (spiderPosition.equals(adjOriginPos.get(7))){
            return spiderPosition.translateBy(Direction.UP);
        }else if (spiderPosition.equals(adjOriginPos.get(0))){
            return spiderPosition.translateBy(Direction.RIGHT);
        }   
        return null;
    }
}
        

