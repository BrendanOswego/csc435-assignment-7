package mainpackage.oauth;

import java.security.Principal;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class OAuthUser implements Principal {
  private String id;
  private List<String> roles;

  public OAuthUser(String id, Array roles) {
    this.id = id;
    try {
      String[] temp = (String[]) roles.getArray();
      this.roles = Arrays.asList(temp);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public OAuthUser(String id, List<String> roles) {
    this.id = id;
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public List<String> getRoles() {
    return roles;
  }

  @Override
  public String getName() {
    return id;
  }

}