package dungeonmania.buildable;

import dungeonmania.Entity;
import dungeonmania.response.models.ItemResponse;

public class BuildableEntity extends Entity{

    private int durability;

    public BuildableEntity(String id, String type) {
        super(id, type, null, false);
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public ItemResponse getItemResponse() {
        return new ItemResponse(getId(), getType());
    }

    
}
