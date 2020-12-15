package via.sdj3.battleshipbackend.Battleship;

import java.awt.*;

public class GameTile {
  private Board board;
  private int x, y;
  private Ship ship = null;
  private boolean wasShot = false;

  public GameTile(int x, int y, Board board) {
    this.x = x;
    this.y = y;
    this.board = board;
  }

  public Ship getShip() {
    return ship;
  }

  public void setShip(Ship ship) {
    this.ship = ship;
  }

  public boolean isShot() {
    return wasShot;
  }

  public boolean shootTile() {
    wasShot = true;
    if (ship != null) {
      ship.shipHit();
      if (!ship.isAlive()) {
        board.shipDestroyed();
      }
      return true;
    }
    return false;
  }
}
