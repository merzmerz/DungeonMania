package dungeonmania;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Goals.AndNode;
import dungeonmania.Goals.GoalFactory;
import dungeonmania.Goals.GoalNode;
import dungeonmania.Goals.OrNode;
import dungeonmania.MovingEntity.*;
import dungeonmania.MovingEntity.Mercenary.Mercenary;
import dungeonmania.MovingEntity.Spider.Spider;
import dungeonmania.MovingEntity.Spider.SpiderSpawn;
import dungeonmania.MovingEntity.ZombieToast.ZombieToast;
import dungeonmania.buildable.Bow;
import dungeonmania.buildable.Build;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.collectable.*;
import dungeonmania.staticentities.StaticEntity;
import dungeonmania.staticentities.ZombieSpawner;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

public class GameManager {
    private Player player;
    private Build build;
    private int tick = 0;
    private int invisibleCounter = 0;
    private DungeonResponse dungeonResponse;
    private String dungeonName;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> inventoryEntities = new ArrayList<>();
    private List<MovingEntity> movingEntities = new ArrayList<>();
    private List<StaticEntity> staticEntities = new ArrayList<>();
    private List<CollectableEntity> collectableEntities = new ArrayList<>();
    private List<EntityResponse> entityResponses = new ArrayList<>();
    private List<ItemResponse> itemResponses = new ArrayList<>();
    private List<String> buildables = new ArrayList<>();
    private Direction moveDirection;
    private boolean moved = false;
    private boolean initSpiderSpawn = false;
    private String goal;
    private GoalNode goalNode;
    private int mapHeight;
    private int mapWidth;
    private BattleManager battleManager;
    private SpiderSpawn spiderSpawn;
    private String itemUsedId;

    public GameManager(String dungeonId, String dungeonName, List<Entity> entities, String goals, int mapHeight, int mapWidth) {
        this.entities = entities;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.goal = goals;
        this.dungeonName = dungeonName;
        makeGoal();
        setupBackend();
        setupFrontend();
        dungeonResponse = new DungeonResponse(dungeonId, dungeonName, entityResponses, new ArrayList<>(), new ArrayList<>(), goal);
        this.battleManager = new BattleManager(this);
        build = new Build(this);
    }

    public void setupBackend() {
        for (int i = 0; i < entities.size(); i++) {
            Entity tmp = entities.get(i);
            if (tmp instanceof Player) {
                player = (Player) tmp;
            } else if (tmp instanceof StaticEntity) {
                staticEntities.add((StaticEntity) tmp);
            } else if (tmp instanceof CollectableEntity) {
                collectableEntities.add((CollectableEntity) tmp);
            } else if (tmp instanceof MovingEntity) {
                movingEntities.add((MovingEntity) tmp);
            } 
        } 
    }

    public void setupFrontend() {
        for (StaticEntity e : staticEntities) {
            entityResponses.add(e.getEntityResponse());
        }
        for (MovingEntity e : movingEntities) {
            entityResponses.add(e.getEntityResponse());
        }
        collectableEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        entityResponses.add(player.getEntityResponse());
    }
 
    public int getTickCounter() {
        return invisibleCounter;
    }

    public void setTickCounter(int tickCounter) {
        this.invisibleCounter = tickCounter;
    }

    public DungeonResponse buildItem(String buildable) {
        List<Entity> entitiesToRemove = build.buildItem(buildable);

        entityResponses = new ArrayList<>();
        itemResponses = new ArrayList<>();
        staticEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        movingEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        collectableEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        
        inventoryEntities.removeAll(entitiesToRemove);

        inventoryEntities.forEach(e -> itemResponses.add(e.getItemResponse()));

        entities = new ArrayList<>();
        staticEntities.forEach(e -> entities.add(e));
        movingEntities.forEach(e -> entities.add(e));
        collectableEntities.forEach(e -> entities.add(e));
        entities.add(player);

        entityResponses.add(player.getEntityResponse());
        this.dungeonResponse = new DungeonResponse(dungeonResponse.getDungeonId(), dungeonResponse.getDungeonName(),
                entityResponses, itemResponses, new ArrayList<>(), this.goal);
        return dungeonResponse;
    }

    public DungeonResponse updateEntities(String itemUsedId, Direction movementDirection) {
        this.moveDirection = movementDirection;
        this.itemUsedId = itemUsedId;
        this.tick++;

        if (initSpiderSpawn == false) {
            spiderSpawn = new SpiderSpawn(entities, mapHeight, mapWidth);
            initSpiderSpawn = true;
        } 
        if ((tick % 40) == 0) {
            movingEntities.add((MovingEntity)CharacterFactory.createEntity("init-mercenary " + tick, "mercenary", Mercenary.spawnAroundEntry(player.getEntityHistory().get(0).getPosition(), this.entities, this.mapHeight, this.mapWidth)));
        }

        if (getTickCounter() > 0) {
            invisibleCounter--;
        }

        if (getTickCounter() == 0 && player.isInvincible() == true) {
            player.setInvincible(false);
        }

        if (getTickCounter() == 0 && player.isInvisible() == true) {
            player.setInvisible(false);
        }

        if (canPass(player.getPosition().translateBy(movementDirection))) {
            player.move(movementDirection);
            moved = true;
        } else {
            moved = false;
        }
        
        for (CollectableEntity curr : collectableEntities) {
            curr.update(this);
        }

        if (itemUsedId != null) {
            for (Entity curr : inventoryEntities) {
                if (curr instanceof CollectableEntity) {
                    CollectableEntity entity = ((CollectableEntity) curr);
                    entity.use(this);
                }
            }
        }

        for (Entity curr : inventoryEntities) {
            if (curr instanceof Sword) {
                ((Sword) curr).updateDurability(this);
            } else if (curr instanceof Armour) {
                ((Armour) curr).updateDurability(this);
            } else if (curr instanceof Bow) {
                ((Bow) curr).updateDurability(this);
            }
        }

        if ((tick % 20) == 0 && (spiderSpawn.getCurrAmount() <= spiderSpawn.getMaxAmount())) {
            Entity addSpider = spiderSpawn.randomSpawn();
            movingEntities.add((Spider)addSpider);
        }

        for (MovingEntity curr : movingEntities) {
            if (curr.getType().equals("spider")) {
                if (!canPassForSpider(curr.move(curr.getPosition()))) {
                    curr.setMovingStrategy("");
                    curr.move(curr.getPosition());
                }
            } else if (curr.getType().equals("zombie_toast")) {
                curr.move(curr.getPosition());
                ZombieToast zomb = (ZombieToast)curr;
                if (!canPass(curr.getPosition())) {
                    curr.setPosition(zomb.getPosFromLastTick());
                }
                zomb.setPosFromLastTick(curr.getPosition());
            } else if (curr.getType().equals("mercenary")) {
                Mercenary mercenary = (Mercenary)curr;
                if (player.getIsInvincible()) {
                    mercenary.setMovingStrategy("scared");
                } else if (player.getIsInvincible()) {
                    mercenary.setMovingStrategy("hostile");
                }
                mercenary.moveMercenary(this.entities, mercenary.getPosition(), this.mapHeight, this.mapWidth);
            }
        }

        ///////////////////////////////////////////////////////////////
        ///////////////////////// Static entities///////////////////////
        ///////////////////////////////////////////////////////////////

        List<StaticEntity> staticEntities = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            Entity curr = entities.get(i);
            if (curr instanceof StaticEntity) {
                staticEntities.add((StaticEntity) curr);
            }
        }

        for (int i = 0; i < staticEntities.size(); i++) {
            StaticEntity entity = staticEntities.get(i);
            if (entity.getType().equals("exit")) {
                entity.update(this);
            }
            if (entity.getType().equals("boulder")) {
                if (movementDirection != null) {
                    if (canPass(entity.getPosition().translateBy(movementDirection))) {
                        if (moved == false) {
                            entity.update(this);
                        }
                    }
                }
            }
            if (entity.getType().equals("switch")) {
                entity.update(this);
            }
            if (entity.getType().equals("door")) {
                entity.update(this);

            }
            if (entity.getType().equals("portal")) {
                entity.update(this);
            }
            if (entity.getType().equals("zombie_toast_spawner")) {
                entity.update(this);
            }
        }

        ///////////////////////////////////////////////////////////////
        ///////////////////////// Static entities///////////////////////
        ///////////////////////////////////////////////////////////////
        battleManager.fight();
        build.updateBuildableItem();

        this.entityResponses = new ArrayList<>();
        this.itemResponses = new ArrayList<>();
        staticEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        movingEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        collectableEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        inventoryEntities.forEach(e -> itemResponses.add(e.getItemResponse()));

        this.entities = new ArrayList<>();
        staticEntities.forEach(e -> entities.add(e));
        movingEntities.forEach(e -> entities.add(e));
        collectableEntities.forEach(e -> entities.add(e));
        entities.add(player);

        makeGoal();
        if (checkGoal()) {
            this.goal = "";
        }
        if(!battleManager.playerIsDeath()) {
            entities.add(player);
            entityResponses.add(player.getEntityResponse());
        }
        
        
        this.dungeonResponse = new DungeonResponse(dungeonResponse.getDungeonId(), dungeonResponse.getDungeonName(),
        entityResponses, itemResponses, buildables, this.goal);

        return dungeonResponse;
    }

    public DungeonResponse interact(String entityId) {
        List<Entity> toBeRemoveList = new ArrayList<>();
        boolean needRemove = false;
        for (Entity en : entities) {
            if (en.getId().equals(entityId) && en.getIsInteractable() == true) {
                if (en instanceof Mercenary) {
                    Mercenary mercenary = (Mercenary)en;
                    int coinCounter = 0;
                    for (Entity bribeGoods : inventoryEntities) {
                        if (bribeGoods.getType().equals("treasure")) {
                            coinCounter++;
                            toBeRemoveList.add(bribeGoods);
                        }
                    }
                    if (Position.isAdjacent(mercenary.getPosition(), player.getPosition()) || (player.getPosition().equals(mercenary.getPosition()))) {
                        if (coinCounter >= mercenary.getBribedAmount()) {
                            needRemove = true;
                            mercenary.setMovingStrategy("ally");
                        }
                    }
                } else if (en instanceof ZombieSpawner) {
                    ZombieSpawner spawner = (ZombieSpawner) en;
                    for (Entity weapons : inventoryEntities) {
                        if (weapons.getType().equals("sword") || weapons.getType().equals("bow")) {
                            if (Position.isAdjacent(spawner.getPosition(), player.getPosition())) {
                                toBeRemoveList.add(en);
                                needRemove = true;
                            }
                        }
                    }
                }
            }
        }

        if (needRemove) {
            staticEntities.removeAll(toBeRemoveList);
            inventoryEntities.removeAll(toBeRemoveList);
        }

        entityResponses = new ArrayList<>();
        itemResponses = new ArrayList<>();
        staticEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        movingEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        collectableEntities.forEach(e -> entityResponses.add(e.getEntityResponse()));
        inventoryEntities.forEach(e -> itemResponses.add(e.getItemResponse()));
        
        entities = new ArrayList<>();
        staticEntities.forEach(e -> entities.add(e));
        movingEntities.forEach(e -> entities.add(e));
        collectableEntities.forEach(e -> entities.add(e));
        entities.add(player);

        entityResponses.add(player.getEntityResponse());
        this.dungeonResponse = new DungeonResponse(dungeonResponse.getDungeonId(), dungeonResponse.getDungeonName(),
        entityResponses, itemResponses, new ArrayList<>(), this.goal);

        return dungeonResponse;
    }

    public List<MovingEntity> getMovingEntites() {
        return this.movingEntities;
    }
    public void setMovingEntites(List<MovingEntity> movingEntites) {
        this.movingEntities = movingEntites;
    }

    public List<StaticEntity> getStaticEntities() {
        return this.staticEntities;
    }

    public DungeonResponse getDungeonResponse() {
        return this.dungeonResponse;
    }
    public ItemResponse find_item(String name) {
        List<ItemResponse> items = dungeonResponse.getInventory(); 
        for (ItemResponse item: items) {
            if (item.getType().equals(name)) {
                return item;
            }
        } 
        return null;
        
    }

    public List<StaticEntity> getStaticEntity() {
        return this.staticEntities;
    }

    public void setStaticEntity(List<StaticEntity> list) {
        this.staticEntities = list;
    }
    
    public List<CollectableEntity> getCollectableEntity() {
        return this.collectableEntities;
    }

    public void setCollectableEntity(List<CollectableEntity> list) {
        this.collectableEntities = list;
    }

    public List<Entity> getInventoryList() {
        return this.inventoryEntities;
    }

    public void setInventoryList(List<Entity> list) {
        this.inventoryEntities = list;
    }
    public List<Entity> getEntityList() {
        return this.entities;
    }
    private boolean canPass(Position newPos) {   
        for (Entity entity : this.entities) {
            if (entity.getPosition().equals(newPos)) {
                if (!entity.getcanPass()) {
                    return false;
                }
            }
        }
        return true;
    }
        
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public boolean getMoved() {
        return moved;
    }
    public void setMoved(boolean move) {
        this.moved = move;
    }
    public Direction getMovement() {
        return this.moveDirection;
    }
    public void setGoal(String goals) {
        this.goal = goals;
    }
    public String getGoal() {
        return this.goal;
    }

    private boolean canPassForSpider(Position newPos) {   
        for (Entity entity : entities) {
            if (entity.getType().equals("boulder") && entity.getPosition().equals(newPos)) {
                return false;
            }
        }
        return true;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getTick() {
        return this.tick;
    }

    public void makeGoal() {
        String JSONstr = null;
        GoalNode goals = null;
        try {
            JSONstr = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");

        } catch (IOException e) {} 

        JSONObject obj = new JSONObject(JSONstr);
        JSONObject goalObj = obj.getJSONObject("goal-condition");
        String goal = goalObj.getString("goal");
        
        goals = GoalFactory.createGoal(goal, this);
        this.goal = ":" + goal;
        
        if (goal.equals("AND")) {
            JSONArray subgoal = goalObj.getJSONArray("subgoals");

            JSONObject e1_goal = subgoal.getJSONObject(0);
            JSONObject e2_goal = subgoal.getJSONObject(1);
            String e1_string = e1_goal.getString("goal");
            String e2_string = e2_goal.getString("goal");

            GoalNode e1 = GoalFactory.createGoal(e1_string, this);
            GoalNode e2 = GoalFactory.createGoal(e2_string, this);

            this.goal = ":" + e1_string + " AND " + ":" + e2_string;
            goals = new AndNode(e1, e2);   
        } else if (goal.equals("OR")) {
            JSONArray subgoal = goalObj.getJSONArray("subgoals");

            JSONObject e1_goal = subgoal.getJSONObject(0);
            JSONObject e2_goal = subgoal.getJSONObject(1);
            String e1_string = e1_goal.getString("goal");
            String e2_string = e2_goal.getString("goal");

            GoalNode e1 = GoalFactory.createGoal(e1_string, this);
            GoalNode e2 = GoalFactory.createGoal(e2_string, this);

            this.goal = ":" + e1_string + " OR " + ":" + e2_string;

            goals = new OrNode(e1, e2);   
        }
        this.goalNode = goals;
    }
    

    public boolean checkGoal() {
        return this.goalNode.compute();
    }

    public List<String> getBuildables() {
        return buildables;
    }

    public void setBuildables(List<String> buildables) {
        this.buildables = buildables;
    }

    public String getItemUsedId() {
        return itemUsedId;
    }
}
