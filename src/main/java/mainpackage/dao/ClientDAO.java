package mainpackage.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Client;

public interface ClientDAO {

  @SqlUpdate("insert into client (client_id, client_secret, app_name, homepage) select :client_id, :client_secret, :app_name, :homepage where not exists(select client_id from client where client_id = :client_id)")
  public void insert(@Bind("client_id") String client_id, @Bind("client_secret") String client_secret,
      @Bind("app_name") String app_name, @Bind("homepage") String homepage);

  @SqlQuery("select * from client where (client_id = :client_id)")
  public Client get(@Bind("client_id") String client_id);

}