package via.sdj3.battleshipbackend.dbAccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import util.Message;
import util.MessageType;
import via.sdj3.battleshipbackend.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Repository("realRepo")
public class UserDbAccess implements UserDao {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;

  public UserDbAccess() {
    try {
      socket = new Socket("localhost", 7990);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      gson = new Gson();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException {
    User userToBeValidated = new User(username, password, "wut");
    String userAsJson = gson.toJson(userToBeValidated);
    Message message = new Message(userAsJson, MessageType.VALIDATE_USER);
    String messageAsJson = gson.toJson(message);
    out.println(messageAsJson);
    try {
      String serverReply = in.readLine();
      Message serverMessage = gson.fromJson(serverReply, Message.class);
      switch (serverMessage.getType()) {
        case VALID_USER:
          return gson.fromJson(serverMessage.getMessage(), User.class);
        case USERNAME_NOT_FOUND:
          throw new InvalidUsernameException("Username not found");
        case INVALID_PASSWORD:
          throw new InvalidPasswordException("Invalid password");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
