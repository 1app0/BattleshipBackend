package via.sdj3.battleshipbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.battleshipbackend.Battleship.BattleshipGameAccess;
import via.sdj3.battleshipbackend.Battleship.Ship;

@Service("battleshipService")
public class BattleshipService {
  private BattleshipGameAccess game;

  @Autowired
  public BattleshipService(BattleshipGameAccess game) {
    this.game = game;
  }

  public int[] getGameConfiguration() {
    return game.getPlacementOfBotShips();
  }

  public boolean verifyPlayerShipPlacement(Ship ship, int x, int y) {
    return game.verifyPlayerShipPlacement(ship, x, y);
  }
}
