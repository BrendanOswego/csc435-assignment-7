package mainpackage.dao;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Book;
import mainpackage.db.BookAuthor;

public interface BookAuthorDAO {

    @SqlUpdate("insert into book_author (book_id, author_id) "
            + "select :book_id, :author_id where not exists (select book_id, author_id from book_author where book_id = :book_id and author_id = :author_id)")
    @GetGeneratedKeys
    public UUID post(@Bind("book_id") UUID book_id, @Bind("author_id") UUID author_id);

    @SqlQuery("select b.id, title, genre, year_published, pages from book b inner join book_author ba on ( b.id = ba.book_id ) "
            + "inner join author a on ( a.id = ba.author_id ) where ( a.id = :author_id )")
    public List<Book> getBooksByAuthor(@Bind("author_id") UUID author_id);

    @SqlUpdate("delete from book_author where (id = :id)")
    public void delete(@Bind("id") UUID id);

    @SqlQuery("select * from book_author where book_id = :book_id and author_id = :author_id")
    public BookAuthor getBookAuthorByID(@Bind("book_id") UUID book_id, @Bind("author_id") UUID author_id);

    @SqlUpdate("delete from book_author where (author_id = :author_id and book_id = :book_id)")
    @GetGeneratedKeys
    public UUID removeBookByAuthor(@Bind("book_id") UUID bookID, @Bind("author_id") UUID authorID);
}