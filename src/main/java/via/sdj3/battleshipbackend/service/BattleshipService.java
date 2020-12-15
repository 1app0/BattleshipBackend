package via.sdj3.battleshipbackend.service;

import org.springframework.stereotype.Service;
import via.sdj3.battleshipbackend.Battleship.BattleshipGame;
import via.sdj3.battleshipbackend.Battleship.Board;

@Service("battleshipService")
public class BattleshipService {
  public Board getGameConfiguration() {
    BattleshipGame game = new BattleshipGame();
    Thread thread = new Thread(game);
    thread.start();

    return game.getEnemyBoard();
  }
}
