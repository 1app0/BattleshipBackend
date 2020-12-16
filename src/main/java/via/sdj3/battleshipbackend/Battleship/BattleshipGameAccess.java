package via.sdj3.battleshipbackend.Battleship;

import via.sdj3.battleshipbackend.model.Coordinate;

import java.util.ArrayList;

public interface BattleshipGameAccess {
  int [] getPlacementOfBotShips();
  boolean verifyPlayerShipPlacement(Ship ship, int x, int y);
  boolean shootTile(int x, int y);
  ArrayList<Coordinate> getGameTilesShotByBot();
}
