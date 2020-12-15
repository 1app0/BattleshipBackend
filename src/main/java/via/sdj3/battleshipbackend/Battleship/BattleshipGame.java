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

  public Board getEnemyBoard() {
    return enemyBoard;
  }

  private void createGame() {
    playerBoard = new Board(false);
    enemyBoard = new Board(true);
  }

  public void botShipPlacement() {
    int type = 5;

    while(type > 0) {
      int x = random.nextInt(10);
      int y = random.nextInt(10);

      if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
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
        //When a client shoots, api receives the information about the shot and sends back information about bot shot, if bot wins sens the correct info
      }
    }
  }

  @Override public void run() {
    botShipPlacement();
  }
}
