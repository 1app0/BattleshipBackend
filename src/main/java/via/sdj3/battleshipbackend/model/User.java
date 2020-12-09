package via.sdj3.battleshipbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
  private final String username;
  private final String password;
  private final String accessType;

  public User(@JsonProperty("Username") String username,
              @JsonProperty("Password") String password,
              @JsonProperty("AccessType") String accessType) {
    this.username = username;
    this.password = password;
    this.accessType = accessType;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getAccessType() {
    return accessType;
  }
}
