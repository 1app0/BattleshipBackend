package via.sdj3.battleshipbackend.dbAccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import via.sdj3.battleshipbackend.model.User;

public interface UserDao {
  User validateUser(String username, String password) throws
      InvalidUsernameException, InvalidPasswordException;
}
