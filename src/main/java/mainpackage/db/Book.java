package mainpackage.db;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "book")
public class Book {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @JsonProperty
  private UUID id;

  @JsonProperty
  private String title;

  @JsonProperty
  private String genre;

  @JsonProperty
  private int year_published;

  @JsonProperty
  private int pages;

  public Book() {
  }

  public Book(UUID id, String title, String genre, int year_published, int pages) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.year_published = year_published;
    this.pages = pages;
  }

  public Book(String title, String genre, int year_published, int pages) {
    this.title = title;
    this.genre = genre;
    this.year_published = year_published;
    this.pages = pages;
  }

  public void setID(UUID id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setYearPublished(int year_published) {
    this.year_published = year_published;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  @JsonIgnore
  public String getTitle() {
    return title;
  }

  @JsonIgnore
  public String getGenre() {
    return genre;
  }

  @JsonIgnore
  public int getYearPublished() {
    return year_published;
  }

  @JsonIgnore
  public int getPages() {
    return pages;
  }

  @JsonIgnore
  public UUID getID() {
    return id;
  }

}