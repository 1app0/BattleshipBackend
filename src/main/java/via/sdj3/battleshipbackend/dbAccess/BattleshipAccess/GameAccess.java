package via.sdj3.battleshipbackend.dbAccess.BattleshipAccess;

import via.sdj3.battleshipbackend.model.GameConfig;

public interface GameAccess {
  String saveGameConfig(GameConfig gameConfig);
  boolean isGameSaved(String username);
  void deleteGameSave(String username);
  GameConfig loadGameConfig(String username);
}
