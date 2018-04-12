package mainpackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mainpackage.dao.AuthorDAO;
import mainpackage.db.Author;
import mainpackage.db.Book;
import mainpackage.controllers.*;

public class MainApplication extends Application<Config> {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(final String[] args) throws Exception {
        new MainApplication().run(args);
    }

    private final HibernateBundle<Config> hibernate = new HibernateBundle<Config>(Author.class, Book.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(Config configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public String getName() {
        return "CSC435 Assignment 6";
    }

    @Override
    public void initialize(final Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final Config configuration, final Environment environment) {
        final AuthorDAO authorDAO = new AuthorDAO(hibernate.getSessionFactory());

        final IndexController index = new IndexController();
        final AuthorController authors = new AuthorController(authorDAO);

        environment.jersey().register(index);
        environment.jersey().register(authors);
    }

}
