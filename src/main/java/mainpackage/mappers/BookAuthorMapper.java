package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.BookAuthor;

public class BookAuthorMapper implements RowMapper<BookAuthor> {

  /**
   * maps a result from query to BookAuthor object
   */
  @Override
  public BookAuthor map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new BookAuthor(UUID.fromString(rs.getString("id")), UUID.fromString(rs.getString("author_id")),
        UUID.fromString(rs.getString("book_id")));
  }

}