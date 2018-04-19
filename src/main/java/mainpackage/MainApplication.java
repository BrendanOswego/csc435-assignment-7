package mainpackage;

import com.github.arteam.jdbi3.JdbiFactory;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import mainpackage.controllers.AuthorController;
import mainpackage.controllers.IndexController;
import mainpackage.controllers.BookController;
import mainpackage.mappers.AuthorMapper;
import mainpackage.mappers.BookAuthorMapper;
import mainpackage.mappers.BookMapper;

public class MainApplication extends Application<Config> {

  public static void main(final String[] args) throws Exception {
    new MainApplication().run(args);
  }

  @Override
  public String getName() {
    return "CSC435 Assignment 6";
  }

  @Override
  public void run(final Config config, final Environment environment) {
    final JdbiFactory factory = new JdbiFactory();
    final Jdbi database = factory.build(environment, config.getDatabase(), "postgresql");

    database.registerRowMapper(new AuthorMapper());
    database.registerRowMapper(new BookMapper());
    database.registerRowMapper(new BookAuthorMapper());

    final IndexController index = new IndexController();
    final AuthorController authors = new AuthorController(database);
    final BookController books = new BookController(database);

    environment.jersey().register(index);
    environment.jersey().register(authors);
    environment.jersey().register(books);
  }

}
