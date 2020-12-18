package via.sdj3.battleshipbackend.Battleship;

import org.springframework.stereotype.Component;
import via.sdj3.battleshipbackend.model.Coordinate;
import via.sdj3.battleshipbackend.model.GameConfig;

import java.util.ArrayList;
import java.util.Random;

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
  //sets if the player has won
  private boolean playerWin = false;

  public BattleshipGame() {
  }

  public void startGame() {
    playerWin = false;
    enemyTurn = false;
    running = false;
    playerBoard = new Board(false);
    enemyBoard = new Board(true);
    botShipPlacement();
  }

  public void loadGameConfig(GameConfig config) {
    //loads the saved config in the database
    int[] playerGameTileConfig = config.getPlayerGameTilesConfig();
    int[] botGameTilesConfig = config.getBotGameTilesConfig();
    int playerShips = config.getPlayerShipsLeft();
    int botShips = config.getBotShipsLeft();
    playerWin = false;
    enemyTurn = false;
    running = true;
    playerBoard = new Board(false);
    enemyBoard = new Board(true);
    playerBoard.setShips(playerShips);
    enemyBoard.setShips(botShips);
    playerBoard.setUpGameTiles(playerGameTileConfig);
    enemyBoard.setUpGameTiles(botGameTilesConfig);
  }

  public boolean shootTile(int x, int y) {
    //after a player receives the coordinates or tiles hit the bot, clear the array
    if (running) {
      gameTilesShotByBot.clear();
      GameTile gameTile = enemyBoard.getGameTile(x, y);
      boolean hit = gameTile.shootTile();
      if (!hit) {
        enemyTurn = true;
        botMove();
        return false;
      }
      if (enemyBoard.getShips() == 0)
      {
        playerWin = true;
      }
      return true;
    }
    return false;
  }

  public boolean verifyPlayerShipPlacement(Ship ship, int x ,int y) {
    boolean canPlace = playerBoard.placeShip(ship, x, y);
    if (ship.getType() == 1 && canPlace) {
      running = true;
    }
    return canPlace;
  }

  public int[] getPlacementOfBotGameTiles() {
    return enemyBoard.getPlacementOfGameTilesInInt();
  }

  public int[] getPlacementOfPlayerGameTiles() {
    return playerBoard.getPlacementOfGameTilesInInt();
  }

  public int getNumberOfShipsLeftBot() {
    return enemyBoard.getShips();
  }

  public int getNumberOfShipsLeftPlayer() {
    return playerBoard.getShips();
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
  }

  private void botMove() {
    while (enemyTurn) {
      int x = random.nextInt(10);
      int y = random.nextInt(10);

      GameTile gameTile = playerBoard.getGameTile(x, y);

      if (gameTile.wasShot()) {
        continue;
      }

      boolean hitTile = gameTile.shootTile();
      boolean botWin = playerBoard.getShips() == 0;
      //whether the bot hits a ship or not, save the hit gameTile coordinates to an array,
      //with whether the bot hit a ship tile or not and if the bot won the game
      Coordinate coordinate = new Coordinate(gameTile.getX(), gameTile.getY(), hitTile, botWin, playerWin);
      gameTilesShotByBot.add(coordinate);
      if (!hitTile) {
        enemyTurn = false;
      }

      if (botWin) {
        System.out.println("YOU DIED");
      }
    }
  }
}
