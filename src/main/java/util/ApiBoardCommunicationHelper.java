package util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiBoardCommunicationHelper {
  private int[] gameTileArray;

  public ApiBoardCommunicationHelper(@JsonProperty("ShipPlacementBot") int[] gameTileArray) {
    this.gameTileArray = gameTileArray;
  }

  public int[] getGameTileArray() {
    return gameTileArray;
  }
}
