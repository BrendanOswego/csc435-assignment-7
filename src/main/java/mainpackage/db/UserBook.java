package mainpackage.db;

import java.util.UUID;

public class UserBook {

  private UUID id;

  private UUID user_id;

  private UUID book_id;

  public UserBook(UUID id, UUID user_id, UUID book_id) {
    this.id = id;
    this.user_id = user_id;
    this.book_id = book_id;
  }

  public UUID getId() {
    return id;
  }

  public UUID getUserId() {
    return user_id;
  }

  public UUID getBookId() {
    return book_id;
  }

}