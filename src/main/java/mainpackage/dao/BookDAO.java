package mainpackage.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import mainpackage.db.Book;

public class BookDAO extends AbstractDAO<Book> {

  public BookDAO(SessionFactory db) {
    super(db);
  }

  public Book findById(Long id) {
    return get(id);
  }

  public Book create(Book book) {
    return persist(book);
  }

}