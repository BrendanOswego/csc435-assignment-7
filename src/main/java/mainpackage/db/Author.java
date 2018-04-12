package mainpackage.db;

import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Author {

  @Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
  private String first_name;
  private String last_name;

  public Author() {
  }

  public Author(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

}