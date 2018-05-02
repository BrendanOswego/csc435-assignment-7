package mainpackage.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import mainpackage.db.Client;

public class ClientMapper implements RowMapper<Client> {

  @Override
  public Client map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Client(rs.getString("client_id"), rs.getString("client_secret"), rs.getString("app_name"),
        rs.getString("homepage"));
  }

}