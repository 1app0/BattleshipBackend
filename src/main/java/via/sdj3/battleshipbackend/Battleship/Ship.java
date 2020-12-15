package via.sdj3.battleshipbackend.Battleship;

public class Ship {
  //type == hitPoints == length of the ship, also controls number of ships
  private int type;
  private boolean isVertical;
  private int hitPoints;

  public Ship(int type, boolean isVertical) {
    this.type = type;
    this.isVertical = isVertical;
    hitPoints = type;
  }

  public int getType() {
    return type;
  }

  public boolean isVertical() {
    return isVertical;
  }

  public void shipHit() {
    hitPoints--;
  }

  public boolean isAlive() {
    return hitPoints > 0;
  }
}
