package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.oauth.Token;

public class TokenMapper implements RowMapper<Token> {

  @Override
  public Token map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Token(rs.getString("access_token"), rs.getString("token_type"), rs.getInt("expires_in"),
        rs.getString("refresh_token"), rs.getString("client_id"));
  }

}