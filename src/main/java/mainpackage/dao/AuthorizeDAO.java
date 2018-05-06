package mainpackage.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Authorization;

public interface AuthorizeDAO {

  @SqlUpdate("insert into authorize (client_id, code, redirect_uri) select :client_id, :code, :redirect_uri where not exists (select client_id from authorize where client_id = :client_id)")
  public void insert(@Bind("client_id") String client_id, @Bind("code") String code,
      @Bind("redirect_uri") String redirect_uri);

  @SqlQuery("select * from authorize where (client_id = :client_id)")
  public Authorization get(@Bind("client_id") String client_id);

}