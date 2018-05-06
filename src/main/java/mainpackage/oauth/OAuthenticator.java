package mainpackage.oauth;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import mainpackage.dao.TokenDAO;

public class OAuthenticator implements Authenticator<String, OAuthUser> {
  Logger logger = LoggerFactory.getLogger(OAuthenticator.class);

  private TokenDAO tokenDAO;

  public OAuthenticator(Jdbi database) {
    tokenDAO = database.onDemand(TokenDAO.class);
  }

  @Override
  public Optional<OAuthUser> authenticate(String token) throws AuthenticationException {
    List<Token> tokens = tokenDAO.get();
    logger.info("TOKEN: " + token);
    for (Token t : tokens) {
      if (t.getAccessToken().equals(token)) {
        long now = Instant.now().getEpochSecond();
        if (now >= t.getExpires()) {
          logger.info("TOKEN EXPIRED");
          return Optional.empty();
        }
        List<String> roles = new ArrayList<>();
        roles.add(OAuthRoles.READ_ONLY);
        return Optional.of(new OAuthUser(t.getClientId(), roles));
      }
    }
    return Optional.empty();
  }

}