package via.sdj3.battleshipbackend.dbAccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import org.springframework.stereotype.Repository;
import via.sdj3.battleshipbackend.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository("testRepository")
public class FakeUserDataAccess implements UserDao {
  private List<User> userList;

  public FakeUserDataAccess() {
    userList = new ArrayList<>();
    userList.add(new User("alex", "1234", "registeredUser"));
    userList.add(new User("shrek", "shrekIsLife", "registeredUser"));
  }

  @Override public User validateUser(String username, String password) throws
      InvalidUsernameException, InvalidPasswordException {
   User filteredUser;
    try {
      filteredUser = userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
    } catch (Exception e) {
      throw new InvalidUsernameException("Username not found");
    }
    if (!filteredUser.getPassword().equals(password)) {
      throw new InvalidPasswordException("Invalid password");
    }
    return filteredUser;
  }
}
