package via.sdj3.battleshipbackend.Battleship;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Board {
  //holds information about the board state, ships and also verifies the location of the ships when trying to place them on the board
  private boolean enemyBoard = false;
  private int ships = 5;
  private final int columns = 10;
  private final int rows = 10;
  private GameTile[] gameTiles = new GameTile[columns * rows];

  public Board(boolean enemyBoard) {
    this.enemyBoard = enemyBoard;
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < columns; x++) {
        GameTile gameTile = new GameTile(x, y, this);
        int index = getIndex(x, y);
        gameTiles[index] = gameTile;
      }
    }
  }

  public int getIndex(int x, int y) {
    return y * columns + x;
  }

  public GameTile getGameTile(int x, int y) {
    int index = getIndex(x, y);
    return gameTiles[index];
  }

  public int getShips() {
    return ships;
  }

  public int getColumns() {
    return columns;
  }

  public int getRows() {
    return rows;
  }

  public boolean placeShip(Ship ship, int x, int y) {
    if (canPlaceShip(ship, x, y)){
      int length = ship.getType();

      if (ship.isVertical()) {
        for (int i = y; i < y + length; i++) {
          GameTile gameTile = getGameTile(x, i);
          gameTile.setShip(ship);
        }
      } else {
        for (int i = x; i < x + length; i++) {
          GameTile gameTile = getGameTile(i, y);
          gameTile.setShip(ship);
        }
      }
      return true;
    }
    return false;
  }

  private boolean canPlaceShip(Ship ship, int x, int y) {
    int length = ship.getType();
    boolean isVertical = ship.isVertical();

    if (isVertical) {
      for (int i = y; i < y + length; i++) {
        if (!isValidPoint(x, i)) {
          return false;
        }

        GameTile gameTile = getGameTile(x, i);

        if (gameTile.getShip() != null) {
          return false;
        }

        for (GameTile neighbour : getNeighbours(x, i)) {
          if (!isValidPoint(x, i)) {
            return false;
          }
          if (neighbour.getShip() != null) {
            return false;
          }
        }
      }
    } else {
      for (int i = x; i < x + length; i++) {
        if (!isValidPoint(i, y)) {
          return false;
        }

        GameTile gameTile = getGameTile(i, y);

        if (gameTile.getShip() != null) {
          return false;
        }

        for (GameTile neighbour : getNeighbours(i, y)) {
          if (!isValidPoint(i, y)) {
            return false;
          }
          if (neighbour.getShip() != null) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private GameTile[] getNeighbours(int x, int y) {
    Point2D[] points = new Point2D[] {
        new Point2D.Double(x -1 , y),
        new Point2D.Double(x + 1, y),
        new Point2D.Double(x, y - 1),
        new Point2D.Double(x, y + 1)
    };
    List<GameTile> neighbours = new ArrayList<>();
    for (Point2D p : points) {
      if (isValidPoint(p.getX(), p.getY())) {
        neighbours.add(getGameTile((int)p.getX(), (int)p.getY()));
      }
    }
    return neighbours.toArray(new GameTile[0]);
  }

  private boolean isValidPoint(double x, double y) {
    return x >= 0 && x < columns && y >= 0 && y < rows;
  }

  public void shipDestroyed() {
    ships--;
  }
}
