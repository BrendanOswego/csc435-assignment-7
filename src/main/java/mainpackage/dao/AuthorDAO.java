package mainpackage.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import mainpackage.db.Author;

public class AuthorDAO extends AbstractDAO<Author> {
  
  public AuthorDAO(SessionFactory db) {
    super(db);
  }

  public Author findById(Long id) {
    return get(id);
  }

  public Author create(Author author) {
    return persist(author);
  }

}