package dungeonmania.staticentities;

import dungeonmania.Observer;
import dungeonmania.util.Position;

public class Wall extends StaticEntity implements Observer {
       
    public Wall(String id, Position position) {
        super(id, "wall", position, false);
        this.setCanPass(false);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object o) {
        
    }
   
}
