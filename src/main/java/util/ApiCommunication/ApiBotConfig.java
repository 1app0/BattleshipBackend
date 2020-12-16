package util.ApiCommunication;

import com.fasterxml.jackson.annotation.JsonProperty;

//helper class for sending bot ship placement
public class ApiBotConfig {
  @JsonProperty("botConfig")
  private int[] botShipPlacement;

  public int[] getBotShipPlacement() {
    return botShipPlacement;
  }

  public void setBotShipPlacement(int[] botShipPlacement) {
    this.botShipPlacement = botShipPlacement;
  }
}
