package via.sdj3.battleshipbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.battleshipbackend.Battleship.BattleshipGameAccess;
import via.sdj3.battleshipbackend.Battleship.Ship;
import via.sdj3.battleshipbackend.dbAccess.BattleshipAccess.GameAccess;
import via.sdj3.battleshipbackend.model.Coordinate;
import via.sdj3.battleshipbackend.model.GameConfig;

import java.util.ArrayList;

@Service("battleshipService")
public class BattleshipService {
  private BattleshipGameAccess game;
  private GameAccess gameAccess;

  @Autowired
  public BattleshipService(BattleshipGameAccess game, GameAccess gameAccess) {
    this.game = game;
    this.gameAccess = gameAccess;
  }

  public void startGame() {
    game.startGame();
  }

  public GameConfig loadGameConfig(String username) {
    GameConfig config  = gameAccess.loadGameConfig(username);
    game.loadGameConfig(config);
    return config;
  }

  public boolean verifyPlayerShipPlacement(Ship ship, int x, int y) {
    return game.verifyPlayerShipPlacement(ship, x, y);
  }

  public boolean shootTile(int x, int y) {
    return game.shootTile(x, y);
  }

  public ArrayList<Coordinate> getGameTilesShotByBot() {
    return game.getGameTilesShotByBot();
  }

  public String saveGameConfig(String username) {
    int[] botConfig = game.getPlacementOfBotGameTiles();
    int[] playerConfig = game.getPlacementOfPlayerGameTiles();
    int botShipsLeft = game.getNumberOfShipsLeftBot();
    int playerShipsLeft = game.getNumberOfShipsLeftPlayer();
    GameConfig gameConfig = new GameConfig(username, botConfig, playerConfig, botShipsLeft, playerShipsLeft);
    return gameAccess.saveGameConfig(gameConfig);
  }

  public boolean isGameSaved(String username) {
    return gameAccess.isGameSaved(username);
  }

  public void deleteGameSave(String username) {
    gameAccess.deleteGameSave(username);
  }
}
