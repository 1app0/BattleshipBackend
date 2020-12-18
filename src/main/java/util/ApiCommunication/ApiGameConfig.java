package util.ApiCommunication;

import com.fasterxml.jackson.annotation.JsonProperty;

//helper class for sending bot ship placement NOT USED
public class ApiGameConfig {
  @JsonProperty("botConfig")
  private int[] botShipPlacement;

  public int[] getBotShipPlacement() {
    return botShipPlacement;
  }

  public void setBotShipPlacement(int[] botShipPlacement) {
    this.botShipPlacement = botShipPlacement;
  }
}
