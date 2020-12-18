package via.sdj3.battleshipbackend.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.ApiCommunication.ApiGameConfig;
import util.ApiCommunication.ApiPlayerShipPlacement;
import util.ApiCommunication.ApiShootGameTileResults;
import via.sdj3.battleshipbackend.Battleship.Ship;
import via.sdj3.battleshipbackend.model.Coordinate;
import via.sdj3.battleshipbackend.model.GameConfig;
import via.sdj3.battleshipbackend.service.BattleshipService;

import java.util.ArrayList;

@RestController
@RequestMapping("/battleShipBot")
public class BattleshipsController {
  private BattleshipService battleshipService;
  private Gson gson;

  @Autowired
  public BattleshipsController(BattleshipService battleshipService) {
    this.battleshipService = battleshipService;
    gson = new Gson();
  }

  @GetMapping("/startGame")
  public void startGame() {
    battleshipService.startGame();
  }

  //TODO implement the correct get game save
  @PostMapping("/loadGameConfig")
  public ResponseEntity getGameConfiguration(@RequestBody String username) {
    GameConfig config = battleshipService.loadGameConfig(username);
    return new ResponseEntity(config, HttpStatus.OK);
  }

  @PostMapping("/checkGameSave")
  public boolean isGameSaved(@RequestBody String username) {
    return battleshipService.isGameSaved(username);
  }

  @PostMapping("/deleteGameSave")
  public void deleteGameSave(@RequestBody String username) {
    battleshipService.deleteGameSave(username);
  }

  @PostMapping("/saveGame")
  public String saveGameConfig(@RequestBody String username) {
    String response = battleshipService.saveGameConfig(username);
    return gson.toJson(response);
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
