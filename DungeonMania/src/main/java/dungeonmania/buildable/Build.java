package dungeonmania.buildable;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entity;
import dungeonmania.GameManager;;

public class Build {
    GameManager gameManager;
    private int woodCounter;
    private int arrowCounter;
    private int treasureCounter;
    private Boolean hasKey;
    private List<String> buildableList;
    private int build_no = 0;

    public Build(GameManager gameManager) {
        this.gameManager = gameManager;
        this.woodCounter = 0;
        this.arrowCounter = 0;
        this.treasureCounter = 0;
        this.hasKey = false;
        this.buildableList = new ArrayList<>();

    }

    public void updateBuildableItem() {
        this.buildableList = new ArrayList<>();
        this.woodCounter = 0;
        this.arrowCounter = 0;
        this.treasureCounter = 0;
        this.hasKey = false;
        for (Entity entity : gameManager.getInventoryList()) {
            if (entity.getType().equals("wood")) { 
                woodCounter++; 
            }
            if (entity.getType().equals("arrow")) {
                arrowCounter++;
            }
            if (entity.getType().equals("treasure")) {
                treasureCounter++;
            }
            if (entity.getType().equals("key")) {
                hasKey = true;
            }

        }

        if (woodCounter >= 1 && arrowCounter >= 3) {
            buildableList.add("bow");
        } 
        if (woodCounter >= 2 && (treasureCounter >= 1 || hasKey)) {
            buildableList.add("shield");
        }
        
        gameManager.setBuildables(buildableList);

    }

    public List<Entity> buildItem(String item) {
        build_no++;
        List<Entity> entitiesToRemove = new ArrayList<>();
        if (item.equals("bow") && (woodCounter >= 1 && arrowCounter >= 3)) {
            int woodRequire = 0;
            int arrowRequire = 0;
            for (Entity entity : gameManager.getInventoryList()) {
                if (entity.getType().equals("wood") && woodRequire < 1) {
                    entitiesToRemove.add(entity);
                    woodRequire++;
                } else if (entity.getType().equals("arrow") && arrowRequire < 3) {
                    entitiesToRemove.add(entity);
                    arrowRequire++;
                }
            }
            List<Entity> tmpInventory =  gameManager.getInventoryList();
            Bow newEntity = new Bow("bow" + build_no);
            if (newEntity != null) {
                tmpInventory.add(newEntity);
            }
            gameManager.setInventoryList(tmpInventory);

            return entitiesToRemove;
        } else if (item.equals("shield") && (woodCounter >= 2 && treasureCounter >= 1)) {
            int woodRequire = 0;
            int treasureRequire = 0;
            for (Entity entity : gameManager.getInventoryList()) {
                if (entity.getType().equals("wood") && woodRequire < 2) {
                    entitiesToRemove.add(entity);
                    woodRequire++;
                } else if (entity.getType().equals("treasure") && treasureRequire < 1) {
                    entitiesToRemove.add(entity);
                    treasureRequire++;
                }
            }
            List<Entity> tmpInventory = gameManager.getInventoryList();
            Shield newEntity = new Shield("shield" + build_no);
            if (newEntity != null) {
                tmpInventory.add(newEntity);
            }
            gameManager.setInventoryList(tmpInventory);

            return entitiesToRemove;
        } else if (item.equals("shield") && (woodCounter >= 2 && hasKey)) {
            int woodRequire = 0;
            int keyRequire = 0;
            for (Entity entity : gameManager.getInventoryList()) {
                if (entity.getType().equals("wood") && woodRequire < 2) {
                    entitiesToRemove.add(entity);
                    woodRequire++;
                } else if (entity.getType().equals("key") && keyRequire < 1) {
                    entitiesToRemove.add(entity);
                    keyRequire++;
                }
            }
            List<Entity> tmpInventory = gameManager.getInventoryList();
            Shield newEntity = new Shield("shield" + build_no);
            if (newEntity != null) {
                tmpInventory.add(newEntity);
            }
            gameManager.setInventoryList(tmpInventory);

            return entitiesToRemove;
        }
        return entitiesToRemove;
    }
}
