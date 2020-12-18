package via.sdj3.battleshipbackend.dbAccess.Mediator;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import util.DbCommunication.Message;
import util.DbCommunication.MessageType;
import via.sdj3.battleshipbackend.model.GameConfig;
import via.sdj3.battleshipbackend.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component("DBAccess")
public class DatabaseAccess implements DBAccess {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;

  public DatabaseAccess() {
    try {
      socket = new Socket("localhost", 7990);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      gson = new Gson();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  @Override public User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException{
    User userToBeValidated = new User(username, password, "w.u.t.");
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
    } catch(IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void registerUser(String username, String password)
      throws UsernameTakenException {
    User userToBeRegistered = new User(username, password, "registeredUser");
    String userAsJson = gson.toJson(userToBeRegistered);
    Message message = new Message(userAsJson, MessageType.REGISTER_USER);
    String messageAsJson = gson.toJson(message);
    out.println(messageAsJson);
    try {
      String serverReply = in.readLine();
      Message serverMessage = gson.fromJson(serverReply, Message.class);
      if (serverMessage.getType().equals(MessageType.REGISTER_USER_USERNAMETAKEN)) {
        throw new UsernameTakenException("Username already taken");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //TODO extract method reading input from database server;
  @Override public String saveGameConfig(GameConfig gameConfig) {
    String gameConfigAsJson = gson.toJson(gameConfig);
    Message message = new Message(gameConfigAsJson, MessageType.SAVEGAME);
    String messageAsJson = gson.toJson(message);
    out.println(messageAsJson);
    try {
      String serverReply = in.readLine();
      Message serverMessage = gson.fromJson(serverReply, Message.class);
      if (serverMessage.getType().equals(MessageType.SAVEGAME_RESPONSE)) {
        return serverMessage.getMessage();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "Something went wrong";
  }

  @Override public boolean isGameSaved(String username) {
    Message message = new Message(username, MessageType.IS_GAME_SAVED);
    String messageAsJson = gson.toJson(message);
    out.println(messageAsJson);
    try {
      String serverReply = in.readLine();
      Message serverMessage = gson.fromJson(serverReply, Message.class);
      if (serverMessage.getType().equals(MessageType.IS_GAME_SAVED_RESPONSE)) {
        return Boolean.parseBoolean(serverMessage.getMessage());
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override public void deleteGameSave(String username) {
    Message message = new Message(username, MessageType.DELETE_GAME_SAVE);
    String messageAsJson = gson.toJson(message);
    out.println(messageAsJson);
  }

  @Override public GameConfig loadGameConfig(String username) {
    Message message = new Message(username, MessageType.LOAD_GAME_CONFIG);
    String messageAsJson = gson.toJson(message);
    out.println(messageAsJson);
    try {
      String serverReply = in.readLine();
      Message serverMessage = gson.fromJson(serverReply, Message.class);
      if (serverMessage.getType().equals(MessageType.LOAD_GAME_CONFIG_RESULT)) {
        return gson.fromJson(serverMessage.getMessage(), GameConfig.class);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
