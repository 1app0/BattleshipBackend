package via.sdj3.battleshipbackend.Battleship;

import via.sdj3.battleshipbackend.model.Coordinate;
import via.sdj3.battleshipbackend.model.GameConfig;

import java.util.ArrayList;

public interface BattleshipGameAccess {
  void startGame();
  int [] getPlacementOfBotGameTiles();
  int [] getPlacementOfPlayerGameTiles();
  int getNumberOfShipsLeftBot();
  int getNumberOfShipsLeftPlayer();
  boolean verifyPlayerShipPlacement(Ship ship, int x, int y);
  boolean shootTile(int x, int y);
  ArrayList<Coordinate> getGameTilesShotByBot();
  void loadGameConfig(GameConfig config);
}
