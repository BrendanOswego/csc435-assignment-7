package mainpackage;

import com.github.arteam.jdbi3.JdbiFactory;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import mainpackage.controllers.AuthorController;
import mainpackage.controllers.AuthorizeController;
import mainpackage.controllers.IndexController;
import mainpackage.controllers.RegisterController;
import mainpackage.controllers.TokenController;
import mainpackage.controllers.UserController;
import mainpackage.controllers.BookController;
import mainpackage.controllers.CallbackController;
import mainpackage.mappers.AuthorMapper;
import mainpackage.mappers.BookAuthorMapper;
import mainpackage.mappers.BookMapper;
import mainpackage.mappers.RegisterMapper;
import mainpackage.mappers.TokenMapper;
import mainpackage.oauth.OAuthUser;
import mainpackage.oauth.OAuthenticator;
import mainpackage.oauth.OAuthorization;

public class MainApplication extends Application<Config> {

  public static void main(final String[] args) throws Exception {
    new MainApplication().run(args);
  }

  @Override
  public String getName() {
    return "CSC435 Assignment 6";
  }

  public void initialize(Bootstrap<Config> bootstrap) {
    bootstrap.addBundle(new AssetsBundle());
    bootstrap.addBundle(new ViewBundle<Config>());
  }

  @Override
  public void run(final Config config, final Environment environment) {
    final JdbiFactory factory = new JdbiFactory();
    final Jdbi database = factory.build(environment, config.getDatabase(), "postgresql");

    database.registerRowMapper(new AuthorMapper());
    database.registerRowMapper(new BookMapper());
    database.registerRowMapper(new BookAuthorMapper());
    database.registerRowMapper(new RegisterMapper());
    database.registerRowMapper(new TokenMapper());

    final IndexController index = new IndexController();
    final AuthorController authors = new AuthorController(database);
    final BookController books = new BookController(database);
    final AuthorizeController authorize = new AuthorizeController(database);
    final RegisterController register = new RegisterController(database);
    final TokenController token = new TokenController(database);
    final CallbackController callback = new CallbackController();
    final UserController user = new UserController();

    environment.jersey().register(index);
    environment.jersey().register(authors);
    environment.jersey().register(books);
    environment.jersey().register(authorize);
    environment.jersey().register(register);
    environment.jersey().register(token);
    environment.jersey().register(callback);
    environment.jersey().register(user);

    environment.jersey()
        .register(new AuthDynamicFeature(
            new OAuthCredentialAuthFilter.Builder<OAuthUser>().setAuthenticator(new OAuthenticator(database))
                .setAuthorizer(new OAuthorization()).setPrefix("Bearer").buildAuthFilter()));

    environment.jersey().register(RolesAllowedDynamicFeature.class);
  }

}
