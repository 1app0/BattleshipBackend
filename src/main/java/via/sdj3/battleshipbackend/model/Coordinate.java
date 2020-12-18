package via.sdj3.battleshipbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {
  @JsonProperty("x")
  private int x;
  @JsonProperty("y")
  private int y;
  @JsonProperty("wasHit")
  private boolean wasHit;
  @JsonProperty("botWin")
  private boolean botWin;
  @JsonProperty("playerWin")
  private boolean playerWin;

  public Coordinate(int x, int y, boolean wasHit, boolean botWin, boolean playerWin) {
    this.x = x;
    this.y = y;
    this.wasHit = wasHit;
    this.botWin = botWin;
    this.playerWin = playerWin;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean getWasHit() {
    return wasHit;
  }

  public boolean getBotWin() {
    return botWin;
  }

  public boolean getPlayerWin() {
    return playerWin;
  }
}
