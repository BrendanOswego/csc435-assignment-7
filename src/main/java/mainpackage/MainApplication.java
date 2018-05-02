package mainpackage;

import javax.servlet.Registration;

import com.github.arteam.jdbi3.JdbiFactory;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import mainpackage.controllers.AuthorController;
import mainpackage.controllers.AuthorizeController;
import mainpackage.controllers.IndexController;
import mainpackage.controllers.RegistrationController;
import mainpackage.controllers.TokenController;
import mainpackage.controllers.UserController;
import mainpackage.controllers.BookController;
import mainpackage.mappers.AuthorMapper;
import mainpackage.mappers.BookAuthorMapper;
import mainpackage.mappers.BookMapper;
import mainpackage.mappers.ClientMapper;
import mainpackage.mappers.UserMapper;

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
    database.registerRowMapper(new UserMapper());
    database.registerRowMapper(new ClientMapper());

    final IndexController index = new IndexController();
    final AuthorController authors = new AuthorController(database);
    final BookController books = new BookController(database);
    final AuthorizeController authorize = new AuthorizeController(database);
    final TokenController token = new TokenController();
    final UserController users = new UserController(database);
    final RegistrationController register = new RegistrationController(database);

    environment.jersey().register(index);
    environment.jersey().register(authors);
    environment.jersey().register(books);
    environment.jersey().register(authorize);
    environment.jersey().register(token);
    environment.jersey().register(users);
    environment.jersey().register(register);

  }

}
