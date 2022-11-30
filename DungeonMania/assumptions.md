Moving Entities
-Assume user will always give EntityResponse as a parameter for instantiate MovingEntities.
-Assume user must instantiate EntityResponse for each of MovingEntities.
-Assume user must provide list of MovingEntities for GameManager after instantiate GameManager.
-Assume user must provid height and width of dungeon in json file.
Collectable Entities
- Assume player can only obtain one copy of the item except for arrow and wood.
- Assume every collectable entity will not spawn at the same location.
- Assume every collectable entity will not be duplicated except for arrow and wood.
- Assume every collectable entity will be discarded after the item is used.
- Bomb will be triggered one tick behind and will destroy all entities except player

For GameManager
-Assume user must provide list of MovingEntities for GameManager after instantiate GameManager.
BattleManager

Assume every item in inventory is equipped and therefore used in a battle
Assume enemy wearing armour halves player's attack
Assume sword increases player's attack by 30
Assume shield decreases enemies attack by 5
Assume player has 100 HP and 10 ATK
Assume spider has 50 HP and 5 ATK
Assume zombie toast has 70 HP and 6 ATK
Assume mercenary has 60 HP and 7 ATK

Static Entities
-Assume there's only one type of key and one type of door
-Assume only the boulder can trigger the switch
-Assume only player can push the boulder
-Assume other entities can walk on the exit, but will not complete the dungeon

Goal
-Assume if goal is not given the dungeon cannot run
-There are 4 goals in the dungeon(enemies, treasure, switch, and exit)
-Goal can have two form of connection which is AND / OR

Buildable Entities
- You can only have one copy of buidable entity in inventory