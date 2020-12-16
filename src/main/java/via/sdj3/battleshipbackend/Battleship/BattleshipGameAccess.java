package via.sdj3.battleshipbackend.Battleship;

public interface BattleshipGameAccess {
  int [] getPlacementOfBotShips();
  boolean verifyPlayerShipPlacement(Ship ship, int x, int y);
}
