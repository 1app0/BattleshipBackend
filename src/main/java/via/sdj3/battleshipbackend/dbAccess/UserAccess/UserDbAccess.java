package via.sdj3.battleshipbackend.dbAccess.UserAccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import via.sdj3.battleshipbackend.dbAccess.Mediator.DBAccess;
import via.sdj3.battleshipbackend.model.User;

@Repository("realAccess")
public class UserDbAccess implements UserAccess {
  private DBAccess dbAccess;

  @Autowired
  public UserDbAccess(DBAccess dbAccess) {
    this.dbAccess = dbAccess;
  }

  @Override public User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException {
    return dbAccess.validateUser(username, password);
  }

  @Override public void registerUser(String username, String password)
      throws UsernameTakenException {
    dbAccess.registerUser(username, password);
  }
}
