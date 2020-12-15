package via.sdj3.battleshipbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ApiBoardCommunicationHelper;
import via.sdj3.battleshipbackend.Battleship.Board;
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
  public ResponseEntity<ApiBoardCommunicationHelper> getGameConfiguration() {
    ApiBoardCommunicationHelper helper = battleshipService.getGameConfiguration();
    return new ResponseEntity(helper, HttpStatus.OK);
  }
}
