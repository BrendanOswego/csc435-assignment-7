package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.Authorization;

public class AuthorizationMapper implements RowMapper<Authorization> {

  @Override
  public Authorization map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Authorization(rs.getString("client_id"), rs.getString("code"), rs.getString("redirect_uri"));
  }

}