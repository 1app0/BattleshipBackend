package via.sdj3.battleshipbackend.dbAccess.UserAccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import via.sdj3.battleshipbackend.model.User;

public interface UserAccess {
  User validateUser(String username, String password) throws
      InvalidUsernameException, InvalidPasswordException;
  void registerUser(String username, String password) throws
      UsernameTakenException;
}
