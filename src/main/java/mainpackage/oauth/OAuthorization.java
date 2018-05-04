package mainpackage.oauth;

import io.dropwizard.auth.Authorizer;

public class OAuthorization implements Authorizer<OAuthUser> {

  @Override
  public boolean authorize(OAuthUser user, String role) {
    return user.getRoles().contains(OAuthRoles.READ_ONLY);
  }

}