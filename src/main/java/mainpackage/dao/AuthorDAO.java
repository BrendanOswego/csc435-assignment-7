package mainpackage.dao;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import mainpackage.db.Author;

public interface AuthorDAO {

    @SqlQuery("select * from author")
    public List<Author> get();

    @SqlQuery("select * from author where (id = :id) limit 1")
    public Author get(@Bind("id") UUID id);

    @SqlUpdate("insert into author (first_name, last_name) "
            + "select :first_name, :last_name where not exists (select first_name, last_name from author where first_name = :first_name and last_name = :last_name)")
    @GetGeneratedKeys
    public UUID post(@Bind("first_name") String first__name, @Bind("last_name") String last_name);

    @SqlUpdate("delete from author where (id = :id)")
    @GetGeneratedKeys
    public UUID delete(@Bind("id") UUID id);

    @SqlUpdate("update author set first_name = :first_name, last_name = :last_name where (id = :id)")
    @GetGeneratedKeys
    public UUID updateAuthor(@Bind("id") UUID id, @Bind("first_name") String first_name,
            @Bind("last_name") String last_name);

    @SqlQuery("select * from author where (first_name = :first_name and last_name = :last_name)")
    public Author get(@Bind("first_name") String first_name, @Bind("last_name") String last_name);
}