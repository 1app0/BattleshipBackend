package via.sdj3.battleshipbackend.api;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.battleshipbackend.model.User;
import via.sdj3.battleshipbackend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/validateUser")
  public ResponseEntity<User> validateUser(@RequestBody User user){
    User validatedUser;
    try {
      validatedUser = userService.validateUser(user.getUsername(), user.getPassword());
    }
    catch (InvalidUsernameException e) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    catch (InvalidPasswordException e) {
      return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<>(validatedUser, HttpStatus.OK);
  }

  @PostMapping("/registerUser")
  public ResponseEntity registerUser(@RequestBody User user){
    try {
      userService.registerUser(user.getUsername(), user.getPassword());
      return new ResponseEntity(HttpStatus.OK);
    }
    catch (UsernameTakenException e) {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }
  }
}
