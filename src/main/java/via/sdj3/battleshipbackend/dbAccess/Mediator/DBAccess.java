package via.sdj3.battleshipbackend.dbAccess.Mediator;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import via.sdj3.battleshipbackend.model.GameConfig;
import via.sdj3.battleshipbackend.model.User;

public interface DBAccess {
  User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException;
  void registerUser(String username, String password)
      throws UsernameTakenException;
  String saveGameConfig(GameConfig gameConfig);
  boolean isGameSaved(String username);
  void deleteGameSave(String username);
  GameConfig loadGameConfig(String username);
}
