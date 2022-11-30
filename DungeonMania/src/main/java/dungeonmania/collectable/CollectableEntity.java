package dungeonmania.collectable;

import dungeonmania.Entity;
import dungeonmania.Observer;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public abstract class CollectableEntity extends Entity implements Observer{

    public CollectableEntity(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
        super.setCanPass(true);
    }

    public ItemResponse getItemResponse() {
        return new ItemResponse(getId(), getType());
    }

    public abstract void use(Object o);
}
