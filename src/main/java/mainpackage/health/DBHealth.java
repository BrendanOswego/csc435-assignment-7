package mainpackage.health;

import com.codahale.metrics.health.HealthCheck;

public class DBHealth extends HealthCheck {

  public DBHealth() {
  }

	@Override
	protected Result check() throws Exception {
		return null;
	}


}