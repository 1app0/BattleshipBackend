package util;

import com.fasterxml.jackson.annotation.JsonProperty;
import via.sdj3.battleshipbackend.Battleship.GameTile;

public class ApiBoardCommunicationHelper {
  private int[][] gameTileArray;

  public ApiBoardCommunicationHelper(@JsonProperty("ShipPlacementBot") GameTile[][] gameTileArray) {
    for (int y = 0; y < gameTileArray.length; y++) {
      for (int x = 0; x < gameTileArray[0].length; x++) {
        //TODO finish this
      }
    }
  }
}
