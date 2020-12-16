package via.sdj3.battleshipbackend.Battleship;

import org.springframework.stereotype.Component;
import via.sdj3.battleshipbackend.model.Coordinate;

import java.util.ArrayList;
import java.util.Random;
//TODO implement api communication
//Threaded class that will be initialized for every game, creating new pairs of boards and ships
@Component
public class BattleshipGame implements BattleshipGameAccess {
  //controls start and end of the game
  private boolean running = false;
  //holds information about the enemy and the player board
  private Board enemyBoard, playerBoard;
  //keeps track of the gameTiles that the bot shot
  private ArrayList<Coordinate> gameTilesShotByBot = new ArrayList<>();
  //controls the turn of the ai
  private boolean enemyTurn = false;
  //controls random ship placement, random shots fired
  private final Random random = new Random();

  public BattleshipGame() {
    playerBoard = new Board(false);
    enemyBoard = new Board(true);
    botShipPlacement();
  }

  public boolean shootTile(int x, int y) {
    //TODO configure from front end so you cant shoot shot gameTiles!!!!!
    gameTilesShotByBot.clear();
    GameTile gameTile = enemyBoard.getGameTile(x, y);
    boolean hit = gameTile.shootTile();
    if (!hit) {
      enemyTurn = true;
      botMove();
      return false;
    }
    return true;
  }

  public boolean verifyPlayerShipPlacement(Ship ship, int x ,int y) {
    return playerBoard.placeShip(ship, x, y);
  }

  public int[] getPlacementOfBotShips() {
    //creates a simple integer array that holds 1 if a ship is on a gameTile and 0 if it's not
    return enemyBoard.getPlacementOfShipsInInt();
  }

  public ArrayList<Coordinate> getGameTilesShotByBot() {
    return gameTilesShotByBot;
  }

  public void botShipPlacement() {
    int type = 5;

    while(type > 0) {
      int x = random.nextInt(10);
      int y = random.nextInt(10);
      Ship ship = new Ship(type, Math.random() < 0.5);
      if (enemyBoard.placeShip(ship, x, y)) {
        type--;
      }
    }
    running = true;
  }

  private void botMove() {
    while (enemyTurn) {
      int x = random.nextInt(10);
      int y = random.nextInt(10);

      GameTile gameTile = playerBoard.getGameTile(x, y);

      if (gameTile.isShot()) {
        continue;
      }

      boolean hitTile = gameTile.shootTile();

      if (hitTile) {
        Coordinate coordinate = new Coordinate(gameTile.getX(), gameTile.getY());
        gameTilesShotByBot.add(coordinate);
      } else {
        Coordinate coordinate = new Coordinate(gameTile.getX(), gameTile.getY());
        gameTilesShotByBot.add(coordinate);
        enemyTurn = false;
      }

      if (playerBoard.getShips() == 0) {
        System.out.println("YOU DIED");
        System.exit(0);
      }
    }
  }
}
