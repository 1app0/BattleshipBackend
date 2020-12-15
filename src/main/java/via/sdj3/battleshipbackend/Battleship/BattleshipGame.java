package via.sdj3.battleshipbackend.Battleship;

import java.util.Random;
//TODO implement api communication
//Threaded class that will be initialized for every game, creating new pairs of boards and ships
public class BattleshipGame implements Runnable {
  //controls start and end of the game
  private boolean running = false;
  //holds information about the enemy and the player board
  private Board enemyBoard, playerBoard;
  //controls the turn of the ai
  private boolean enemyTurn = false;
  //controls random ship placement, random shots fired
  private Random random = new Random();

  public BattleshipGame() {
    playerBoard = new Board(false);
    enemyBoard = new Board(true);
  }

  private void createGame() {
    playerBoard = new Board(false);
    enemyBoard = new Board(true);
  }

  public int[] getPlacementOfBotShips() {
    //creates a simple integer array that holds 1 if a ship is on a gameTile and 0 if it's not
    int[] botShipPlacement = new int[enemyBoard.getRows() * enemyBoard.getColumns()];
    for (int y = 0; y < enemyBoard.getRows(); y++) {
      for (int x = 0; x < enemyBoard.getColumns(); x++) {
        int index = enemyBoard.getIndex(x, y);
        if (enemyBoard.getGameTile(x, y).getShip() == null) {
          botShipPlacement[index] = 0;
        } else {
          botShipPlacement[index] = 1;
        }
      }
    }
    return botShipPlacement;
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
      enemyTurn = gameTile.shootTile();
      if (playerBoard.getShips() == 0) {
        System.out.println("YOU DIED");
        System.exit(0);
        //When a client shoots, api receives the information about the shot and sends back information about bot shot,
        // if bot wins sends the correct info
      }
    }
  }

  @Override public void run() {
    botShipPlacement();
  }
}
