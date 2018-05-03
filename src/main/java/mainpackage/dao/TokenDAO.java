package mainpackage.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Token;

public interface TokenDAO {

  @SqlUpdate("insert into token (client_id, access_token, refresh_token, expires_in) select :client_id, :access_token, :refresh_token, :expires_in"
      + " where not exists(select client_id from token where client_id = :client_id)")
  public void insert(@Bind("client_id") String client_id, @Bind("access_token") String access_token,
      @Bind("refresh_token") String refresh_token, @Bind("expires_in") int expires_in);

  @SqlQuery("select * from token where (client_id = :client_id)")
  public Token get(@Bind("client_id") String client_id);

}