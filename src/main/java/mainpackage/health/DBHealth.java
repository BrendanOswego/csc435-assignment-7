package mainpackage.health;

import com.codahale.metrics.health.HealthCheck;

import org.jdbi.v3.core.Jdbi;

public class DBHealth extends HealthCheck {

	private final Jdbi database;

	public DBHealth(Jdbi database) {
		this.database = database;
	}

	@Override
	protected Result check() throws Exception {
		return null;
	}

	public Jdbi getDatabase() {
		return database;
	}

}