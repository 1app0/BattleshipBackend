package via.sdj3.battleshipbackend.dbAccess.BattleshipAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import via.sdj3.battleshipbackend.dbAccess.Mediator.DBAccess;
import via.sdj3.battleshipbackend.model.GameConfig;

@Repository
public class GameDbAccess implements  GameAccess{
  private DBAccess dbAccess;

  @Autowired
  public GameDbAccess(DBAccess dbAccess) {
    this.dbAccess = dbAccess;
  }

  @Override public String saveGameConfig(GameConfig gameConfig) {
    return dbAccess.saveGameConfig(gameConfig);
  }

  @Override public boolean isGameSaved(String username) {
    return dbAccess.isGameSaved(username);
  }

  @Override public void deleteGameSave(String username) {
    dbAccess.deleteGameSave(username);
  }

  @Override public GameConfig loadGameConfig(String username) {
    return dbAccess.loadGameConfig(username);
  }
}
