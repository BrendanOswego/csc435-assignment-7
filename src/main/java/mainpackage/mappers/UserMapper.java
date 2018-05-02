package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.User;

public class UserMapper implements RowMapper<User> {

  @Override
  public User map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new User(UUID.fromString(rs.getString("id")), rs.getString("username"), rs.getString("password"));
  }

}