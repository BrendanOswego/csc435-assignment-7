package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.Author;

public class AuthorMapper implements RowMapper<Author> {

  /**
   * maps a result from query to Author object
   */
  @Override
  public Author map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Author(UUID.fromString(rs.getString("id")), rs.getString("first_name"), rs.getString("last_name"));
  }

}