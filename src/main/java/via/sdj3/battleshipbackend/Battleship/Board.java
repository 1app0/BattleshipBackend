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

  private int getIndex(int x, int y) {
    return y * columns + x;
  }

  public GameTile getGameTile(int x, int y) {
    int index = getIndex(x, y);
    return gameTiles[index];
  }

  public int getShips() {
    return ships;
  }

  public void setShips(int ships) {
    this.ships = ships;
  }

  public int[] getPlacementOfGameTilesInInt() {
    //creates a simple integer array that holds:
    //0 for gameTile no ship
    //1 for gameTile no ship hit
    //2 for gameTile with ship type = 1
    //3 for gameTile with ship type = 2
    //4 for gameTIle with ship type = 3
    //5 for gameTile with ship type = 4
    //6 for gameTile with ship type = 5
    //7 for gameTile with ship hit type = 1
    //8 for gameTile with ship hit type = 2
    //9 for gameTile with ship hit type = 3
    //10 for gameTile with ship hit type = 4
    //11 for gameTile with ship hit type = 5
    int [] gameTilesPlacement = new int[rows * columns];
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < columns; x++) {
        int index = getIndex(x, y);
        GameTile gameTile = getGameTile(x, y);
        if (gameTile.getShip() == null) {
          gameTilesPlacement[index] = 0;
        }
        if (gameTile.getShip() == null && gameTile.wasShot()) {
          gameTilesPlacement[index] = 1;
        }
        if (gameTile.getShip() != null) {
          switch (gameTile.getShip().getType()) {
            case 1:
              gameTilesPlacement[index] = 2;
              break;
            case 2:
              gameTilesPlacement[index] = 3;
              break;
            case 3:
              gameTilesPlacement[index] = 4;
              break;
            case 4:
              gameTilesPlacement[index] = 5;
              break;
            case 5:
              gameTilesPlacement[index] = 6;
              break;
          }
        }
        if (gameTile.getShip() != null && gameTile.wasShot()) {
          switch (gameTile.getShip().getType()) {
            case 1:
              gameTilesPlacement[index] = 7;
              break;
            case 2:
              gameTilesPlacement[index] = 8;
              break;
            case 3:
              gameTilesPlacement[index] = 9;
              break;
            case 4:
              gameTilesPlacement[index] = 10;
              break;
            case 5:
              gameTilesPlacement[index] = 11;
              break;
          }
        }
      }
    }
    return gameTilesPlacement;
  }

  public void setUpGameTiles(int[] config){
    //loads config
    //0 for gameTile no ship
    //1 for gameTile no ship hit
    //2 for gameTile with ship type = 1
    //3 for gameTile with ship type = 2
    //4 for gameTIle with ship type = 3
    //5 for gameTile with ship type = 4
    //6 for gameTile with ship type = 5
    //7 for gameTile with ship hit type = 1
    //8 for gameTile with ship hit type = 2
    //9 for gameTile with ship hit type = 3
    //10 for gameTile with ship hit type = 4
    //11 for gameTile with ship hit type = 5
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < columns; x++) {
        int index = getIndex(x, y);
        switch (config[index]) {
          case 0:
            GameTile gameTile = new GameTile(x, y, this);
            gameTiles[index] = gameTile;
            break;
          case 1:
            GameTile gameTileShot = new GameTile(x, y, this);
            gameTileShot.setWasShot(true);
            gameTiles[index] = gameTileShot;
            break;
          case 2:
            GameTile gameTileShip1 = new GameTile(x, y, this);
            gameTileShip1.setShip(new Ship(1, true));
            gameTiles[index] = gameTileShip1;
            break;
          case 3:
            GameTile gameTileShip2 = new GameTile(x, y, this);
            gameTileShip2.setShip(new Ship(2, true));
            gameTiles[index] = gameTileShip2;
            break;
          case 4:
            GameTile gameTileShip3 = new GameTile(x, y ,this);
            gameTileShip3.setShip(new Ship(3, true));
            gameTiles[index] = gameTileShip3;
            break;
          case 5:
            GameTile gameTileShip4 = new GameTile(x, y, this);
            gameTileShip4.setShip(new Ship(4, true));
            gameTiles[index] = gameTileShip4;
            break;
          case 6:
            GameTile gameTileShip5 = new GameTile(x, y, this);
            gameTileShip5.setShip(new Ship(5, true));
            gameTiles[index] = gameTileShip5;
            break;
          case 7:
            GameTile gameTileShipHit1 = new GameTile(x, y ,this);
            gameTileShipHit1.setShip(new Ship(1, true));
            gameTileShipHit1.setWasShot(true);
            gameTiles[index] = gameTileShipHit1;
            break;
          case 8:
            GameTile gameTileShipHit2 = new GameTile(x, y, this);
            gameTileShipHit2.setShip(new Ship(2, true));
            gameTileShipHit2.setWasShot(true);
            gameTiles[index] = gameTileShipHit2;
            break;
          case 9:
            GameTile gameTileShipHit3 = new GameTile(x, y, this);
            gameTileShipHit3.setShip(new Ship(3, true));
            gameTileShipHit3.setWasShot(true);
            gameTiles[index] = gameTileShipHit3;
            break;
          case 10:
            GameTile gameTileShipHit4 = new GameTile(x, y, this);
            gameTileShipHit4.setShip(new Ship(4, true));
            gameTileShipHit4.setWasShot(true);
            gameTiles[index] = gameTileShipHit4;
            break;
          case 11:
            GameTile gameTileShipHit5 = new GameTile(x, y, this);
            gameTileShipHit5.setShip(new Ship(5, true));
            gameTileShipHit5.setWasShot(true);
            gameTiles[index] = gameTileShipHit5;
            break;
        }
      }
    }
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
        new Point2D.Double(x, y + 1),
        new Point2D.Double(x + 1, y + 1),
        new Point2D.Double(x + 1, y -1),
        new Point2D.Double(x - 1, y + 1),
        new Point2D.Double(x - 1, y - 1)
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
