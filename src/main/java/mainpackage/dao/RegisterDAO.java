package mainpackage.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Register;

public interface RegisterDAO {

  @SqlQuery("select * from register")
  public List<Register> get();

  @SqlQuery("select * from register where (client_id = :client_id)")
  public Register get(@Bind("client_id") String client_id);

  @SqlUpdate("insert into register (client_id, client_secret) select :client_id, :client_secret where not exists(select client_id from register where client_id = :client_id)")
  public void insert(@Bind("client_id") String client_id, @Bind("client_secret") String client_secret);
}