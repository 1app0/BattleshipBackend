package via.sdj3.battleshipbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.ApiCommunication.ApiBotConfig;
import util.ApiCommunication.ApiPlayerShipPlacement;
import util.ApiCommunication.ApiShootGameTileResults;
import via.sdj3.battleshipbackend.Battleship.Ship;
import via.sdj3.battleshipbackend.model.Coordinate;
import via.sdj3.battleshipbackend.service.BattleshipService;

import java.util.ArrayList;

@RestController
@RequestMapping("/battleShipBot")
public class BattleshipsController {
  private BattleshipService battleshipService;

  @Autowired
  public BattleshipsController(BattleshipService battleshipService) {
    this.battleshipService = battleshipService;
  }

  @GetMapping("/startGame")
  public ResponseEntity getGameConfiguration() {
    ApiBotConfig botConfig = new ApiBotConfig();
    botConfig.setBotShipPlacement(battleshipService.getGameConfiguration());
    return new ResponseEntity(botConfig, HttpStatus.OK);
  }

  @PostMapping("/playerPlaceShip")
  public boolean verifyPlayerShipPlacement(@RequestBody ApiPlayerShipPlacement helper) {
    Ship shipToBePlaced = new Ship(helper.getShipType(), helper.isVertical());
    return battleshipService.verifyPlayerShipPlacement(shipToBePlaced, helper.getX(), helper.getY());
  }

  @PostMapping("/shootGameTile")
  public ResponseEntity shootGameTile(@RequestBody Coordinate coordinateTileShot) {
    boolean wasHit = battleshipService.shootTile(coordinateTileShot.getX(), coordinateTileShot.getY());
    ArrayList<Coordinate> gameTilesShotByBot = battleshipService.getGameTilesShotByBot();
    ApiShootGameTileResults results = new ApiShootGameTileResults();
    results.setCoordinates(gameTilesShotByBot);
    results.setWasHit(wasHit);
    return new ResponseEntity(results, HttpStatus.OK);
  }
}
