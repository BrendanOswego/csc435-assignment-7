package mainpackage;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Config extends Configuration {

    /**
     * used to instantiate the config for the datasource
     * e.g the driver, username, password, url,
     * not the actual DB
     */
    @Valid
    @NotNull
    private DataSourceFactory db = new DataSourceFactory();

    @JsonProperty("database")
    public void setDB(DataSourceFactory db) {
        this.db = db;
    }

    @JsonProperty("database")
    public DataSourceFactory getDatabase() {
        return db;
    }

}
