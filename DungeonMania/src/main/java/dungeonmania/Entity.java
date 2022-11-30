package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Entity {
    private String id;
    private String type;
    private Position position;
    private boolean isInteractable;
    private boolean canPass;
    private List<Entity> entityHistory;

    public Entity(String id, String type, Position position, boolean isInteractable) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInteractable = isInteractable;
        this.entityHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean getcanPass() {
        return canPass;
    }

    public void setCanPass(boolean canPass) {
        this.canPass = canPass;
    }

    public EntityResponse getEntityResponse() {
        return new EntityResponse(id, type, position, isInteractable);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemResponse getItemResponse() {
        return new ItemResponse(getId(), getType());
    }

    public boolean getIsInteractable() {
        return this.isInteractable;
    }

    public void setIsInteractable(Boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    public void addToEntityHistory(Entity entity) {
        entityHistory.add(entity);
    }

    public void setEntityHistory(List<Entity> entityHistory) {
        this.entityHistory = entityHistory;
    }

    public List<Entity> getEntityHistory() {
        return entityHistory;
    }
}
