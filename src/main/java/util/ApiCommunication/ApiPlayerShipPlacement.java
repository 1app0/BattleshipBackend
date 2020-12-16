package util.ApiCommunication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiPlayerShipPlacement {
  @JsonProperty("shipType")
  private int shipType;
  @JsonProperty("x")
  private int x;
  @JsonProperty("y")
  private int y;
  @JsonProperty("isVertical")
  private boolean isVertical;

  public int getShipType() {
    return shipType;
  }

  public void setShipType(int shipType) {
    this.shipType = shipType;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean isVertical() {
    return isVertical;
  }

  public void setVertical(boolean vertical) {
    isVertical = vertical;
  }
}
