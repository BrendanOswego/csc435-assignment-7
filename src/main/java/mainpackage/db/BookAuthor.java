package mainpackage.db;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "book_author")
public class BookAuthor {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  @JsonProperty
  private UUID id;

  @JsonProperty
  private UUID book_id;

  @JsonProperty
  private UUID author_id;

  public BookAuthor(UUID id, UUID author_id, UUID book_id) {
    this.id = id;
    this.author_id = author_id;
    this.book_id = book_id;
  }

  @JsonIgnore
  public UUID getAuthorID() {
    return author_id;
  }

  @JsonIgnore
  public UUID getBookID() {
    return book_id;
  }

}