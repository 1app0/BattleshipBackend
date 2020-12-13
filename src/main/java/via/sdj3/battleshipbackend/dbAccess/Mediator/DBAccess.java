package via.sdj3.battleshipbackend.dbAccess.Mediator;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import via.sdj3.battleshipbackend.model.User;

import java.util.concurrent.ExecutionException;

public interface DBAccess {
  User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException;
  void registerUser(String username, String password)
      throws UsernameTakenException;
}
