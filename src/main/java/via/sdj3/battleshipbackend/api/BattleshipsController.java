package via.sdj3.battleshipbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.ApiCommunication.ApiBotConfig;
import util.ApiCommunication.ApiPlayerShipPlacement;
import via.sdj3.battleshipbackend.Battleship.Ship;
import via.sdj3.battleshipbackend.service.BattleshipService;

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
}
