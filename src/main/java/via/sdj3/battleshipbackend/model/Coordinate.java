package via.sdj3.battleshipbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {
  @JsonProperty("x")
  private int x;
  @JsonProperty("y")
  private int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
