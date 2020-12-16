package util.ApiCommunication;

import com.fasterxml.jackson.annotation.JsonProperty;
import via.sdj3.battleshipbackend.model.Coordinate;

import java.util.ArrayList;

//helper class for sending the results of shooting a gameTile
public class ApiShootGameTileResults {
  @JsonProperty("coordinates")
  private ArrayList<Coordinate> coordinates;
  @JsonProperty("hit")
  private boolean wasHit;

  public ArrayList<Coordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(ArrayList<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  public boolean isWasHit() {
    return wasHit;
  }

  public void setWasHit(boolean wasHit) {
    this.wasHit = wasHit;
  }
}
