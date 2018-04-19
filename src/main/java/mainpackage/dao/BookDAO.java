package mainpackage.dao;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Book;

public interface BookDAO {

  @SqlQuery("select * from book")
  public List<Book> get();

  @SqlQuery("select * from book where (id = :id)")
  public Book get(@Bind("id") UUID id);

  @SqlQuery("select * from book where title = :title and genre = :genre and year_published = :year_published and pages = :pages")
  public Book get(@Bind("title") String title, @Bind("genre") String genre, @Bind("year_published") int year_published,
      @Bind("pages") int pages);

  @SqlUpdate("insert into book (title, genre, year_published, pages) "
      + "select :title, :genre, :year_published, :pages "
      + "where not exists (select title, genre from book where title = :title and genre = :genre)")
  @GetGeneratedKeys
  public UUID post(@Bind("title") String title, @Bind("genre") String genre, @Bind("year_published") int year_published,
      @Bind("pages") int pages);

  @SqlUpdate("delete from book where (id = :id)")
  public void delete(@Bind("id") UUID id);

  @SqlUpdate("update book as b set title = :title, genre = :genre, year_published = :year_published, pages = :pages where (id = :id)")
  public void updateBook(@Bind("id") UUID id, @Bind("title") String title, @Bind("genre") String genre,
      @Bind("year_published") int year_published, @Bind("pages") int pages);
}