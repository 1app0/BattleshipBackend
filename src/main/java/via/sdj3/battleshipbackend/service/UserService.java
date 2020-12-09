package via.sdj3.battleshipbackend.service;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import via.sdj3.battleshipbackend.dbAccess.UserDao;
import via.sdj3.battleshipbackend.model.User;

@Service("userService")
public class UserService {
  private UserDao userDao;

  @Autowired
  public UserService(@Qualifier("realRepo") UserDao userDao) {
    this.userDao = userDao;
  }

  public User validateUser(String username, String password)
      throws InvalidPasswordException, InvalidUsernameException {
    return userDao.validateUser(username, password);
  }
}
