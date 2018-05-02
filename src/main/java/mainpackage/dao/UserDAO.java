package mainpackage.dao;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.User;

public interface UserDAO {

  @SqlUpdate("insert into users (username, password) "
      + "select :username, md5(:password) where not exists(select username from users where username = :username)")
  @GetGeneratedKeys
  public UUID post(@Bind("username") String username, @Bind("password") String password);

  @SqlQuery("select * from users")
  public List<User> get();

  @SqlQuery("select * from users where (id = :id)")
  public User get(@Bind("id") UUID id);

}