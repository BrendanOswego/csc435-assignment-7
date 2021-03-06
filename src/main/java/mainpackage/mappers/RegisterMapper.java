package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.Register;

public class RegisterMapper implements RowMapper<Register> {

  @Override
  public Register map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Register(rs.getString("client_id"), rs.getString("client_secret"));
  }

}