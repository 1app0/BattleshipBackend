package via.sdj3.battleshipbackend.service;

import org.springframework.stereotype.Service;
import util.ApiBoardCommunicationHelper;
import via.sdj3.battleshipbackend.Battleship.BattleshipGame;
import via.sdj3.battleshipbackend.Battleship.Board;

@Service("battleshipService")
public class BattleshipService {
  private BattleshipGame game;

  public ApiBoardCommunicationHelper getGameConfiguration() {
    game = new BattleshipGame();
    game.botShipPlacement();

    return new ApiBoardCommunicationHelper(game.getPlacementOfBotShips());
  }
}
