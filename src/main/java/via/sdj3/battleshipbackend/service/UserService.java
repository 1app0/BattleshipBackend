package via.sdj3.battleshipbackend.service;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import via.sdj3.battleshipbackend.dbAccess.UserAccess.UserAccess;
import via.sdj3.battleshipbackend.model.User;

@Service("userService")
public class UserService {
  private UserAccess userAccess;

  @Autowired
  public UserService(@Qualifier("realAccess") UserAccess userAccess) {
    this.userAccess = userAccess;
  }

  public User validateUser(String username, String password)
      throws InvalidPasswordException, InvalidUsernameException {
    return userAccess.validateUser(username, password);
  }

  public void registerUser(String username, String password)
      throws UsernameTakenException {
    userAccess.registerUser(username, password);
  }
}
