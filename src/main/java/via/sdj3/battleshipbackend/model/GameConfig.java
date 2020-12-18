package via.sdj3.battleshipbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameConfig {
  @JsonProperty("username")
  private String username;
  @JsonProperty("botGameTilesConfig")
  private int[] botGameTilesConfig;
  @JsonProperty("playerGameTilesConfig")
  private int[] playerGameTilesConfig;
  @JsonProperty("botShipsLeft")
  private int botShipsLeft;
  @JsonProperty("playerShipsLeft")
  private int playerShipsLeft;

  public GameConfig(String username, int[] botGameTilesConfig, int[] playerGameTilesConfig, int botShipsLeft, int playerShipsLeft) {
    this.username = username;
    this.botGameTilesConfig = botGameTilesConfig;
    this.playerGameTilesConfig = playerGameTilesConfig;
    this.botShipsLeft = botShipsLeft;
    this.playerShipsLeft = playerShipsLeft;
  }

  public int[] getBotGameTilesConfig() {
    return botGameTilesConfig;
  }

  public void setBotGameTilesConfig(int[] botGameTilesConfig) {
    this.botGameTilesConfig = botGameTilesConfig;
  }

  public int[] getPlayerGameTilesConfig() {
    return playerGameTilesConfig;
  }

  public void setPlayerGameTilesConfig(int[] playerGameTilesConfig) {
    this.playerGameTilesConfig = playerGameTilesConfig;
  }

  public int getBotShipsLeft() {
    return botShipsLeft;
  }

  public void setBotShipsLeft(int botShipsLeft) {
    this.botShipsLeft = botShipsLeft;
  }

  public int getPlayerShipsLeft() {
    return playerShipsLeft;
  }

  public void setPlayerShipsLeft(int playerShipsLeft) {
    this.playerShipsLeft = playerShipsLeft;
  }
}
