package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.Book;

public class BookMapper implements RowMapper<Book> {

  /**
   * maps a result from query to Book object
   */
  @Override
  public Book map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Book(UUID.fromString(rs.getString("id")), rs.getString("title"), rs.getString("genre"),
        rs.getInt("year_published"), rs.getInt("pages"));
  }

}