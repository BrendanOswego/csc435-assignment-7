package mainpackage.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.oauth.Token;

public interface TokenDAO {

    @SqlUpdate("insert into token (access_token, token_type, expires_in, refresh_token, client_id) select :access_token, :token_type, :expires_in, :refresh_token, :client_id "
            + "where not exists(select access_token from token where access_token = :access_token)")
    public void insert(@Bind("access_token") String access_token, @Bind("token_type") String token_type,
            @Bind("expires_in") int expires_in, @Bind("refresh_token") String refresh_token,
            @Bind("client_id") String client_id);

    @SqlQuery("select * from token where (access_token = :access_token)")
    public Token get(@Bind("access_token") String access_token);

    @SqlUpdate("delete from token where (access_token = :access_token)")
    public void delete(@Bind("access_token") String access_token);

    @SqlQuery("select * from token")
    public List<Token> get();
}