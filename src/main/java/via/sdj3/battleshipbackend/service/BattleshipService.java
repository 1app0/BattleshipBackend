package via.sdj3.battleshipbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.battleshipbackend.Battleship.BattleshipGameAccess;
import via.sdj3.battleshipbackend.Battleship.Ship;
import via.sdj3.battleshipbackend.model.Coordinate;

import java.util.ArrayList;

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

  public boolean shootTile(int x, int y) {
    return game.shootTile(x, y);
  }

  public ArrayList<Coordinate> getGameTilesShotByBot() {
    return game.getGameTilesShotByBot();
  }
}
